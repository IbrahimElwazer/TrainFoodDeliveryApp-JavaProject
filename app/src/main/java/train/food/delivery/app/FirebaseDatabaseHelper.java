package train.food.delivery.app;

import android.app.DownloadManager;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class FirebaseDatabaseHelper {
    private FirebaseDatabase database;
    private Query restaurants;

    private List<Restaurant> restaurantList = new ArrayList<>();

    int numberOfRestaurant = 20;
    int randomIndex = (int) (Math.random() * numberOfRestaurant);
    public FirebaseDatabaseHelper() {
        database = FirebaseDatabase.getInstance();
        restaurants = database.getReference("restaurants").orderByChild("name").limitToFirst(randomIndex);
    }

    public interface DataStatus {
        void DataIsLoaded (List<Restaurant> restaurantList , List<String> keys);
    }
    public void readRestaurant(final DataStatus dataStatus) {
        restaurants.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                restaurantList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Restaurant restaurant = keyNode.getValue(Restaurant.class);
                    restaurantList.add(restaurant);

                }
                Collections.shuffle(restaurantList);
                dataStatus.DataIsLoaded(restaurantList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}