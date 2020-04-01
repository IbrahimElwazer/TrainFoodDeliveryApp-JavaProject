package train.food.delivery.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (RecyclerView)findViewById(R.id.recycle_restaurantList);
        new FirebaseDatabaseHelper().readRestaurant(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Restaurant> restaurantList, List<String> keys) {
                new ListRestaurant().setConfig(mRecycleView, MainActivity.this, restaurantList,keys);
            }
        });
    }

}
