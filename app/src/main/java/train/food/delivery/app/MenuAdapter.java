package train.food.delivery.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    Context mContext;
    ArrayList<Menu> menuList;

    public MenuAdapter(Context mContext, ArrayList<Menu> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        holder.item.setText(menuList.get(position).getItem());
        holder.price.setText(menuList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


     class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView item, price;

         public MenuViewHolder(@NonNull View itemView) {
             super(itemView);

             item = itemView.findViewById(R.id.menu_item_name);
             price = itemView.findViewById(R.id.menu_item_price);

         }
     }

}
