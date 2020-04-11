package train.food.delivery.app;

import android.util.Log;

public class TrainStation {
    private String stationName;
    private double longitude , latitude;
    private String date;
    public String getStationName() {
        return stationName;
    }
    public double getLatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setStationName(String stationName) {
        Log.i("station" , stationName);
        this.stationName = stationName;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
