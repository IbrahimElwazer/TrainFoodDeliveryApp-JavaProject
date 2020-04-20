package train.food.delivery.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListRestaurant {
    private Context mContext;
    private RestaurantAdapter mRestaurantAdapter;
    public void setConfig(@NonNull RecyclerView recyclerView , Context context , List<Restaurant> restaurantList , List<String> keys) {
        mContext = context;
        mRestaurantAdapter = new RestaurantAdapter(restaurantList,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRestaurantAdapter);

    }



    class ViewRestaurant extends RecyclerView.ViewHolder {
        private TextView RestaurantName;
        private TextView MenuID;

        private String keys;

        public ViewRestaurant(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.show_restaurant, parent, false));

            RestaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
            MenuID = (TextView) itemView.findViewById(R.id.menu_ID);

            RestaurantName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    System.out.println("Hello there");

                    String menuID = MenuID.getText().toString();

                    Intent intent = new Intent(v.getContext(), RestaurantMenuActivity.class);
                    intent.putExtra("menuID", menuID);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bind(Restaurant restaurants , String keys ) {
            RestaurantName.setText(restaurants.getName());
            MenuID.setText(String.valueOf(restaurants.getMenuID()));
            this.keys = keys;
        }

    }


    class RestaurantAdapter extends RecyclerView.Adapter<ViewRestaurant> {
        private List<Restaurant> ListRestaurant;
        private List<String> mKeys;

        public RestaurantAdapter(List<Restaurant> ListRestaurant, List<String> mKeys) {
            this.ListRestaurant = ListRestaurant;
            this.mKeys = mKeys;

        }

        @Override
        public ViewRestaurant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewRestaurant(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewRestaurant holder, int position) {
            holder.bind(ListRestaurant.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return ListRestaurant.size();
        }
    }
}