package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


import edu.scranton.nesbittj3.lunchilicious.MenuItem;

public class MenuFragmentAdapter extends RecyclerView.Adapter<MenuFragmentAdapter.MyViewHolder> {
    private Context context;
    private List<MenuItem> menuItems;
    private MenuItem item;
    private TextView textView;


    MenuFragmentAdapter(Context context, TextView textView) {
        menuItems = new ArrayList<>();
        this.context = context;
        this.menuItems = menuItems;
        this.textView = textView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(menuItems.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }


    public void setMenuItems(List<MenuItem> menuItems){
        this.menuItems = menuItems;
        notifyDataSetChanged();
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

        public void bind(MenuItem menuItems){

            type.setText("" + menuItems.type);
            name.setText("" + menuItems.name);
            unitPrice.setText("" + menuItems.unitPrice);

        }
    }
}
