package train.food.delivery.app;

import android.os.Bundle;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantMenuActivity extends AppCompatActivity {

    DatabaseReference database;
    RecyclerView recyclerView;
    ArrayList<Menu> MenuList;
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_menu_list);

        MenuList = new ArrayList<Menu>();

        database = FirebaseDatabase.getInstance().getReference().child("menu");

        recyclerView = findViewById(R.id.recycler_view_menu);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Menu menu = ds.getValue(Menu.class);
                    MenuList.add(menu);
                }
                adapter = new MenuAdapter(RestaurantMenuActivity.this, MenuList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RestaurantMenuActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }


}
