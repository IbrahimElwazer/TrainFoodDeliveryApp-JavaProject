package train.food.delivery.app;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity {


    DatabaseReference database;
    RecyclerView recyclerView;
    ArrayList<Menu> menuList;
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_menu_list);

        recyclerView = findViewById(R.id.recycler_view_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuList = new ArrayList<Menu>();


        database = FirebaseDatabase.getInstance().getReference("menus");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    menuList.clear();
                   for(DataSnapshot ds : dataSnapshot.getChildren()) {
                       for(DataSnapshot datasnapshot : ds.getChildren()){
                            String item = datasnapshot.getValue(Menu.class).getItem();
                            String price = datasnapshot.getValue(Menu.class).getPrice();

                            Menu results = new Menu(item, price);
                               menuList.add(results);
                        }
                   }
                   adapter = new MenuAdapter(RestaurantMenuActivity.this, menuList);
                   recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Something went wrong on line 39 in RestaurantMenuActivity");
            }
        });
    }
}
