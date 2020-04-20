package train.food.delivery.app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import java.lang.reflect.Method;
import java.util.List;


public class RestaurantListActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);
        mRecycleView = (RecyclerView)findViewById(R.id.recycle_restaurantList);
        new FirebaseDatabaseHelper().readRestaurant(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Restaurant> restaurantList, List<String> keys) {
                new ListRestaurant().setConfig(mRecycleView, RestaurantListActivity.this, restaurantList,keys);

            }
        });


    }
}