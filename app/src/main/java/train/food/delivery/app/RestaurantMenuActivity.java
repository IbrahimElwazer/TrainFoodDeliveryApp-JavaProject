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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity {


    DatabaseReference database;
    RecyclerView recyclerView;
    ArrayList<Menu> menuList;
    MenuAdapter adapter;
    String menuID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_menu_list);

        if(getIntent() != null){
            menuID = getIntent().getStringExtra("menuID");
        }
        if(!menuID.isEmpty() && menuID != null){
            loadRestaurantMenu(menuID);
        }

    }

    private void loadRestaurantMenu(String menuID) {

        recyclerView = findViewById(R.id.recycler_view_menu);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        menuList = new ArrayList<Menu>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        database = FirebaseDatabase.getInstance().getReference("menus").child(menuID);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                menuList.clear();
                    for(DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                        String item = datasnapshot.getValue(Menu.class).getItem();
                        String price = datasnapshot.getValue(Menu.class).getPrice();
                        String image = datasnapshot.getValue(Menu.class).getImage();
                        Menu results = new Menu(item, price, image);

                        menuList.add(results);
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