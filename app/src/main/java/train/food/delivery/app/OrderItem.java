package train.food.delivery.app;

import android.util.Log;

public class OrderItem {
    private String name;
    private float price;
    private int quantity;

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            this.quantity = quantity;
        }
        else
        {
            Log.i("error","error");
        }

    }
    public float getTotal()
    {
        float price = this.price * this.quantity;
        return price;
    }

}
