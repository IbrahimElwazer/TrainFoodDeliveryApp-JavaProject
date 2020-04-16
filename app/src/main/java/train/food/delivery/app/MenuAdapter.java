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
    ArrayList<Menu> menu;

    public MenuAdapter(Context mContext, ArrayList<Menu> menu) {
        this.mContext = mContext;
        this.menu = menu;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.menu_item_name.setText(menu.get(position).getItem());
        holder.menu_item_price.setText(menu.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView menu_item_name, menu_item_price;

        public MenuViewHolder(View itemView) {
            super(itemView);

            menu_item_name = itemView.findViewById(R.id.menu_item_name);
            menu_item_price = itemView.findViewById(R.id.menu_item_price);

        }
    }
}
