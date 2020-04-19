package train.food.delivery.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<OrderItem> {
    public OrderAdapter(Context context, ArrayList<OrderItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        OrderItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order, parent, false);
            Log.i("step1","miss");
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView quantity= (TextView) convertView.findViewById(R.id.quantity);
        // Populate the data into the template view using the data object
        name.setText(item.getName());
        price.setText(Double.toString(item.getPrice()));
        quantity.setText(Integer.toString(item.getQuantity()));
        // Return the completed view to render on screen
        return convertView;
    }
}

