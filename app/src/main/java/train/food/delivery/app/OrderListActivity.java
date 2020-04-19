package train.food.delivery.app;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        ButtonCLick();

    }
    private void ButtonCLick()
    {
        OrderList orderList = new OrderList();
        OrderItem item = new OrderItem();
        OrderItem item2 = new OrderItem();
        item.setName("Mc Donald");
        item.setPrice(10.5);
        item.setQuantity(1);
        orderList.addItem(item);
        item2.setName("KFC");
        item2.setPrice(5);
        item2.setQuantity(2);
        orderList.addItem(item2);
        ArrayList<OrderItem> hello = (ArrayList) orderList.getOrderList();
        for(int i=0;i<hello.size();i++)
        {
            Log.i("item",hello.get(i).getName());
        }
        OrderAdapter adapter = new OrderAdapter(this, hello);
        ListView listView = (ListView) findViewById(R.id.orderList);
        listView.setAdapter(adapter);

    }
}
