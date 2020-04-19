package train.food.delivery.app;

import android.util.Log;

public class OrderItem {
    private String name;
    private double price;
    private int quantity;

    public double getPrice() {
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 1) {
            this.quantity = quantity;
        }
        else
        {
            Log.i("error","error");
        }

    }
    public double getTotal()
    {
        double price = this.price * this.quantity;
        return price;
    }

}
