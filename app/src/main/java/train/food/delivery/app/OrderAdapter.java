package train.food.delivery.app;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<OrderItem> {
    private OrderList hello;
    private Activity hello1 = new OrderListActivity();
    public OrderAdapter(Context context, OrderList items) {
        super(context, 0, items.getOrderList());
        this.hello = items;
    }
    private Button addButton= null;
    private Button substrastButton= null;
    private Button removeButton= null;

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
        addButton = (Button)convertView.findViewById(R.id.addQuantity);
        addButton.setTag(position);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int newQ = item.getQuantity() + 1;
                hello.changeQuantity(newQ , item);
                quantity.setText(Integer.toString(newQ));
            }
        });
        substrastButton = (Button)convertView.findViewById(R.id.substrastQuantity);
        substrastButton.setTag(position);
        substrastButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int newQ = item.getQuantity() - 1;
                if (newQ < 1)
                {
                    hello.removeItem(item);
                }
                else {
                    hello.changeQuantity(newQ, item);
                }
                quantity.setText(Integer.toString(newQ));
            }
        });
        removeButton = (Button)convertView.findViewById(R.id.removeItem);
        removeButton.setTag(position);
        removeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int newQ = 0;
                hello.removeItem(item);
                quantity.setText(Integer.toString(newQ));
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}

