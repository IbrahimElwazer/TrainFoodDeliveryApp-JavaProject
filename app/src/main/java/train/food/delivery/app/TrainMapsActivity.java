package train.food.delivery.app;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TrainMapsActivity extends FragmentActivity implements OnMapReadyCallback , View.OnClickListener {

    private GoogleMap mMap;
    RequestQueue queue = null;
    ArrayList<TrainStation> station = new ArrayList<TrainStation>();

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
    protected void loadStation() {
        String station_url = "https://rata.digitraffic.fi/api/v1/metadata/stations";
        final TextView netResult = findViewById(R.id.button);

        JsonArrayRequest stationRequest = new JsonArrayRequest(Request.Method.GET,
                station_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String string = object.getString("stationName");
                        TrainStation stationMarker = new TrainStation();
                        stationMarker.setStationName(object.getString("stationName"));
                        stationMarker.setLongitude(Double.parseDouble(object.getString("longitude")));
                        stationMarker.setLatitude(Double.parseDouble(object.getString("latitude")));
                        station.add(stationMarker);
                        Log.i("station", string);
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
        Log.i("hello","hello");
    }
    protected void loadTrain()
    {
        String train_url = "https://rata.digitraffic.fi/api/v1/trains/2020-04-01/24?version=0";
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
                        Log.i("arrival station", station);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.getLocalizedMessage());
            }
        }
        );
        queue.add(trainRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        loadTrain();
        loadStation();
        /*TrainStation Oulu = new TrainStation();
        Oulu.setStationName("Oulu");
        Oulu.setLatitude(65.012332);
        Oulu.setLongitude(25.484675);
        station.add(Oulu);
        TrainStation Tampere = new TrainStation();
        Tampere.setStationName("Tampere");
        Tampere.setLatitude(61.498657);
        Tampere.setLongitude(23.773124);
        station.add(Tampere);*/
        setContentView(R.layout.activity_train_maps);
        findViewById(R.id.button).setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng finland = new LatLng(60.1699, 24.9384);
        mMap.addMarker(new MarkerOptions().position(finland).title("Helsinki asema"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(finland));
        for(int i=0; i<station.size(); i++)
        {
            LatLng newStation = new LatLng(station.get(i).getLatitude(), station.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(newStation).title(station.get(i).getStationName()));
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button)
        {
           loadTrain();
        }
    }
}
