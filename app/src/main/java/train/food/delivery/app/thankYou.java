package train.food.delivery.app;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class thankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you);
        TextView number = findViewById(R.id.orderNumber);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(thankYou.this,"XD",Toast.LENGTH_SHORT).show();
            }
        });
        number.setText((int) (Math.floor(Math.random() * 1000000)));
    }

}