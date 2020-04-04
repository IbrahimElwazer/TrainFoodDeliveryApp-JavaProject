package train.food.delivery.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListRestaurant {
    private Context mContext;
    private RestaurantAdapter mRestaurantAdapter;
    public void setConfig(RecyclerView recyclerView , Context context , List<Restaurant> restaurantList , List<String> keys) {
        mContext = context;
        mRestaurantAdapter = new RestaurantAdapter(restaurantList,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRestaurantAdapter);
    }


    class ViewRestaurant extends RecyclerView.ViewHolder {
        private TextView RestaurantName;

        private String keys;

        public ViewRestaurant(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.show_restaurant, parent, false));

            RestaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        }

        public void bind(Restaurant restaurants , String keys ) {
            RestaurantName.setText(restaurants.getName());
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

