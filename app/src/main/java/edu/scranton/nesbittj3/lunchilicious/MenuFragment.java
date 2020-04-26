package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuFragmentAdapter adapter;
    private List<MenuItem> menuItems;
    private MenuViewModel viewModel;
    private Button add;
    private TextView textView;
    private TextView inName;
    private TextView inPrice;
    private TextView inType;


    public static MenuFragment newInstance(){
        return new MenuFragment();
    }

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Context context = view.getContext();
        menuItems = getMenuItems();

        inName = view.findViewById(R.id.inName);
        inPrice = view.findViewById(R.id.inPrice);
        inType = view.findViewById(R.id.inType);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
        adapter = new MenuFragmentAdapter(context, menuItems, textView);
        recyclerView.setAdapter(adapter);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                CharSequence name = inName.getText();
                String p = inPrice.getText().toString();
                float price = Float.parseFloat(p);
                CharSequence type = inType.getText();
                int id = getId(menuItems);
                viewModel.addMenuItem(menuItems, id, type, name, price);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        viewModel.getMenuItemsLiveData().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                adapter.setMenuItems(menuItems);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //mediator = (Mediator) context;
    }

    public ArrayList<MenuItem> getMenuItems() {

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(3, "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(4, "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(5, "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(6, "Pizza", "Plain", "cheese and tomato", (float) 9.50));
        items.add(new MenuItem(7, "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
        items.add(new MenuItem(8, "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
        items.add(new MenuItem(9, "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
        items.add(new MenuItem(10, "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
        items.add(new MenuItem(11, "Side", "Fries", "large hot fries", (float) 2.95));
        items.add(new MenuItem(12, "Side", "Gravy Fries",  "Fries with gravy on top", (float) 3.95));
        items.add(new MenuItem(13, "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
        items.add(new MenuItem(14, "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
        items.add(new MenuItem(15, "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));

        return items;
    }

    public int getId(List<MenuItem> menuItems){
        int id = 0;
        for(int i = 0; i<menuItems.size(); i++){
            id = id+ 1;
        }
        return id;
    }
}
