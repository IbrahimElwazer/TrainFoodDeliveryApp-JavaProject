package train.food.delivery.app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Button button = findViewById(R.id.home_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thankYou.this, TrainInputActivity.class);
                startActivity(intent);
            }
        });
        number.setText(String.valueOf((int) (Math.floor(Math.random() * 1000000))));
    }

}