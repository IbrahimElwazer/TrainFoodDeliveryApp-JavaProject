package train.food.delivery.app;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TrainStopModel {
    private ArrayList<TrainStation> stations = new ArrayList<TrainStation>();

    void addNewTodoItem(ArrayList<TrainStation> stationList) {
        for(int i=0; i<stationList.size();i++)
        {
            stations.add(stationList.get(i));
            Log.i("i",Integer.toString(stationList.size()));
        }
    }

    void removeList(){
        for( int i = 0; i<stations.size(); i++)
        {
            stations.remove(stations.get(i));
        }
    }
    public List<TrainStation> getStation() {
        return stations;
    }

}
