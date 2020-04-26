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

        inName = view.findViewById(R.id.inName);
        inPrice = view.findViewById(R.id.inPrice);
        inType = view.findViewById(R.id.inType);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));

        adapter = new MenuFragmentAdapter(context, textView);
        recyclerView.setAdapter(adapter);
        showMenu(null);
        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addItem(view);
            }
        });

        return view;
    }


    public void showMenu(View v){
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        adapter = new MenuFragmentAdapter(getContext(), textView);
        recyclerView.setAdapter(adapter);
        viewModel.getMenuItemsLiveData().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems){
                adapter.setMenuItems(menuItems);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void addItem(View v){
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        String name = inName.getText().toString();
        String p = inPrice.getText().toString();
        float price = Float.parseFloat(p);
        String type = inType.getText().toString();
        int id = adapter.getItemCount();
        id = id + 1;
        MenuItem menuItem = new MenuItem(id, type, name, "", price);
        viewModel.addMenuItem(menuItem);
    }

}
