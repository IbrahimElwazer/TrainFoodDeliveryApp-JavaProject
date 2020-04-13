package train.food.delivery.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class TrainInputActivity extends AppCompatActivity implements View.OnClickListener {

    RequestQueue queue = null;
    ArrayList<TrainStation> station = new ArrayList<TrainStation>();
    ArrayList<String> times = new ArrayList<String>();
    ArrayList<String> trainStops = new ArrayList<String>();
    ArrayList<String> commercialStops = new ArrayList<String>();
    String departureID;
    String arrivalID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.map).setOnClickListener(this);
        queue = Volley.newRequestQueue(this);
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    };

    protected void translate() {
        String station_url = "https://rata.digitraffic.fi/api/v1/metadata/stations";
        EditText departureInput = findViewById(R.id.departure_city_input);
        EditText arrivalInput = findViewById(R.id.arrival_city_input);
        String departureCity = departureInput.getText().toString();
        String arrivalCity = arrivalInput.getText().toString();
        Log.i("departCity",departureCity);
        JsonArrayRequest stationRequest = new JsonArrayRequest(Request.Method.GET,
                station_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int h = 0; h < response.length(); h++) {
                    try {
                        JSONObject object = response.getJSONObject(h);
                        String string = object.getString("stationName");
                        Log.i("string", string);
                        if (string.equals(departureCity)) {
                            departureID = object.getString("stationShortCode");
                            Log.i("departureID", departureID);
                        } else {
                            if (string.equals(arrivalCity)) {
                                arrivalID = object.getString("stationShortCode");
                            }
                        }
                    } catch (JSONException e) {
                        Log.i("error","can't get input info");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.getLocalizedMessage());
            }
        }
        );
        queue.add(stationRequest);
    }

    protected void loadStation() {
        String station_url = "https://rata.digitraffic.fi/api/v1/metadata/stations";
        final TextView netResult = findViewById(R.id.button);
        JsonArrayRequest stationRequest = new JsonArrayRequest(Request.Method.GET,
                station_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        for (int j = 0;j < trainStops.size(); j ++) {
                            JSONObject object = response.getJSONObject(i);
                            String string = object.getString("stationShortCode");
                            if ((string.equals(trainStops.get(j)))&& commercialStops.get(j).equals("true")) {
                                TrainStation stationMarker = new TrainStation();
                                stationMarker.setStationName(object.getString("stationName"));
                                stationMarker.setLongitude(Double.parseDouble(object.getString("longitude")));
                                stationMarker.setLatitude(Double.parseDouble(object.getString("latitude")));
                                stationMarker.setDate(times.get(j));
                                station.add(stationMarker);
                                Log.i("time", times.get(j));
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.getLocalizedMessage());
            }
        }
        );

        queue.add(stationRequest);
        Log.i("loadStation","hello");
    }
    protected void testLog()
    {
        Log.i("hello","hello");
    }
    protected void loadTrain()
    {
        String train_url = "https://rata.digitraffic.fi/api/v1/live-trains/station/" + departureID + "/" + arrivalID;

        JsonArrayRequest trainRequest = new JsonArrayRequest(Request.Method.GET,
                train_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //JSONArray jsonArray = response.getJSONArray("departureDate");
                try {
                    JSONObject train = response.getJSONObject(0);
                    JSONArray timeTableList = train.getJSONArray("timeTableRows");
                    for (int i = 0; i < timeTableList.length(); i++) {
                        JSONObject timeAtStation = timeTableList.getJSONObject(i);
                        String station = timeAtStation.getString("stationShortCode");
                        String stop = timeAtStation.getString("trainStopping");
                        String time = timeAtStation.getString("scheduledTime");
                        times.add(time);
                        commercialStops.add(stop);
                        trainStops.add(station);
                        Log.i("arrival station", station);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", "error");
            }
        }
        );
        queue.add(trainRequest);
        Log.i("loadTrain","hello");
        Log.i("departure", departureID);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.map)
        {
            //queue = Volley.newRequestQueue(this);
            translate();
            setTimeout(this::loadTrain, 20000);
            //setTimeout(this::translate, 1000);
            setTimeout(this::loadStation,30000);
            TrainStopModel model = TrainStopApplication.getModel(this);
            setTimeout(()->model.addNewTodoItem(station),40000);
            //setTimeout(()-> ,5000);;
            //testLog();
            Intent intent = new Intent(this, TrainMapsActivity.class);
            setTimeout(()->startActivity(intent),50000);
        }
    }
    }
