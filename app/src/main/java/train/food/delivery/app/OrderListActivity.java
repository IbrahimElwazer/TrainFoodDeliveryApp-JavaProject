package train.food.delivery.app;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {
    private static OrderListActivity instance;
    public OrderList orderList = new OrderList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        instance = this;
        ButtonCLick();
        Button payment = findViewById(R.id.payment);
        payment.setOnClickListener(v -> startActivity(new Intent(OrderListActivity.this, OrderPaymentActivity.class)));
    }


    public static OrderListActivity getInstance() {
        return instance;
    }

    private void ButtonCLick()
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
    public void updateUI(View view)
    {
        OrderList list2 = new OrderList();

        double totalMoney = 0;
        ArrayList<OrderItem> hello = (ArrayList) list2.getOrderList();
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
