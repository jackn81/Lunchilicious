package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


import edu.scranton.nesbittj3.lunchilicious.MainActivity.MenuItem;

public class MenuFragmentAdapter extends RecyclerView.Adapter<MenuFragmentAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<MainActivity.MenuItem> menuItems;


    MenuFragmentAdapter(Context context, ArrayList<MenuItem> menuItems, TextView textView) {
        this.context = context;
        this.menuItems = menuItems;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(menuItems.get(position));
    }

    @Override
    public int getItemCount(){
        return menuItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView name;
        private TextView unitPrice;

        MyViewHolder(@NonNull View view) {
            super(view);
            type = view.findViewById(R.id.type);
            name = view.findViewById(R.id.name);
            unitPrice = view.findViewById(R.id.unitPrice);
        }

        public void bind(MenuItem menuItem){

            type.setText("" + menuItem.type);
            name.setText("" + menuItem.name);
            unitPrice.setText("" + menuItem.unitPrice);
        }
    }
}
