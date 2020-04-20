package train.food.delivery.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OrderPaymentActivity extends AppCompatActivity {

    Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_payment);
        btnConfirm = (Button) findViewById(R.id.confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderPaymentActivity.this,"Thank you, your order is on the way",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
