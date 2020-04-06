package train.food.delivery.app;

import android.app.Activity;
import android.app.Application;

public class TrainStopApplication extends Application {
    private TrainStopModel model = null;

    @Override
    public void onCreate() {
        super.onCreate();
        model = new TrainStopModel();
    }

    static public TrainStopModel getModel(Activity activity) {
        TrainStopApplication app = (TrainStopApplication)activity.getApplication();
        return app.model;
    }
}
