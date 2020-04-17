package train.food.delivery.app;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();

    public ArrayList<OrderItem> getOrderList() {
        return orderList;
    }
    public void addItem(OrderItem item)
    {
        if(item != null)
        {
            orderList.add(item);
        }
    }
    public void removeItem(OrderItem item)
    {
        orderList.remove(item);
    }
    public void changeQuantity(int quantity, OrderItem item)
    {
        for (int i=0; i<orderList.size();i++)
        {
            if(orderList.get(i) == item)
            {
                item.setQuantity(quantity);
                orderList.set(i, item);
            }
        }
    }
    public float moneyCalcuation()
    {
        float total = 0;
        for(int i=0; i<orderList.size();i++)
        {
            total =  total + orderList.get(i).getTotal();
        }
        return total;
    }
}
