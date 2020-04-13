package train.food.delivery.app;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TrainMapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnInfoWindowClickListener, LocationListener {

    private GoogleMap mMap;
    RequestQueue queue = null;
    ArrayList<TrainStation> station = new ArrayList<TrainStation>();
    ArrayList<String> trainStops = new ArrayList<String>();
    ArrayList<String> commercialStops = new ArrayList<String>();
    protected LocationManager locationManager;
    protected double latitude, longitude;

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    ;

    /*protected void loadStation() {
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
                                station.add(stationMarker);
                                Log.i("station", object.getString("stationName"));
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
                        String stop = timeAtStation.getString("trainStopping");
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
                Log.i("error", error.getLocalizedMessage());
            }
        }
        );
        queue.add(trainRequest);
        Log.i("loadTrain","hello");
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //loadTrain();
        //loadStation();
        //setTimeout(this::loadStation,1000);
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
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        Log.i("test time2", strDate);
        findViewById(R.id.button).setOnClickListener(this);
        TrainStopModel model = TrainStopApplication.getModel(this);
        for(int i=0;i< model.getStation().size();i++)
        {
            station.add(model.getStation().get(i));
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this) ;
        //setTimeout(this::printMap,0);

    }
    /*public void printMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }*/

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
        setTimeout(()->Log.i("hello","loadMap"),1000);
        // Add a marker in Sydney and move the camera
        LatLng finland = new LatLng(60.1699, 24.9384);
        LatLng currentLocation = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(finland));
        for(int i=0; i<station.size(); i++)
        {
            LatLng newStation = new LatLng(station.get(i).getLatitude(), station.get(i).getLongitude());

            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String s2 = null;
            Date formattedDate = null;
            long diff = 0;
            try {
                formattedDate = inputFormatter.parse(station.get(i).getDate());
                Date date = Calendar.getInstance().getTime();
                diff = (formattedDate.getTime() - date.getTime())/60000;
                s2 = Long.toString(diff);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i("time dif",s2);
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            String strDate = dateFormat.format(formattedDate);
            Log.i("time2", strDate);
            if(diff > 30) {
                mMap.addMarker(new MarkerOptions().position(newStation).title(station.get(i).getStationName()).snippet(strDate));
                mMap.setOnInfoWindowClickListener(this);
            }
            else
            {
                mMap.addMarker(new MarkerOptions().position(newStation).title(station.get(i).getStationName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.setOnInfoWindowClickListener(this);
            }
        }
        /*googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });*/
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button)
        {
            //loadTrain();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if(marker.getSnippet() == null)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(TrainMapsActivity.this).create();
            alertDialog.setTitle("You cannot order from this station");
            alertDialog.setMessage("The time of ordering food must be 30 minutes before station arrival");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else {
            Log.i("marker click", marker.getSnippet());
            Intent intent = new Intent(this, RestaurantListActivity.class);
            intent.putExtra("station",marker.getTitle());
            startActivity(intent);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","enable");
    }
}
