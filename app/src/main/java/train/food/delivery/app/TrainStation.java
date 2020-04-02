package train.food.delivery.app;

import android.util.Log;

public class TrainStation {
    private String stationName;
    private double longitude , latitude;

    public String getStationName() {
        return stationName;
    }
    public double getLatitude() {
        return latitude;
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
}
