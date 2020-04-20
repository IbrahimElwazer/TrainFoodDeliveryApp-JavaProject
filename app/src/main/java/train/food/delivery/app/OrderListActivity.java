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

    public OrderList orderList = new OrderList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        ButtonCLick();

    }
    private void ButtonCLick()
    {
        double totalMoney = 0;
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
        totalMoney = totalMoney + orderList.moneyCalcuation();
        OrderAdapter adapter = new OrderAdapter(this, orderList);
        ListView listView = (ListView) findViewById(R.id.orderList);
        listView.setAdapter(adapter);
        TextView total = (TextView) findViewById(R.id.totalMoney);
        total.setText("Total: " + Double.toString(totalMoney));
    }
    public void updateUI(View view)
    {
        double totalMoney = 0;
        ArrayList<OrderItem> hello = (ArrayList) orderList.getOrderList();
        for(int i=0;i<hello.size();i++)
        {
            Log.i("item",hello.get(i).getName());
        }
        totalMoney = totalMoney + orderList.moneyCalcuation();
        OrderAdapter adapter = new OrderAdapter(this, orderList);
        ListView listView = (ListView) findViewById(R.id.orderList);
        listView.setAdapter(adapter);
        TextView total = (TextView) findViewById(R.id.totalMoney);
        total.setText("Total: " + Double.toString(totalMoney));
    }
}
