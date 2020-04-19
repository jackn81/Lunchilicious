package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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


public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuFragmentAdapter adapter;
    private Mediator mediator;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private MenuViewModel viewModel;
    private Button add;
    private TextView inName;
    private TextView inPrice;
    private TextView inType;
    public static MenuFragment newInstance(){
        return new MenuFragment();
    }

    public MenuFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        viewModel.getMenuItemsLiveData().observe(this, new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem menuItem) {

                adapter.setMenuItems(menuItems);
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //viewModel = new ViewModelProvider(this.getActivity()).get(MainActivity.MyViewModel.class);
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //pull up fragment with input
                //int id = getMenuCount(menuItems);
                String name = (String) inName.getText();
                float price = 1;
                //float price = inPrice.getText();
                String type = (String) inType.getText();

                setName(name);
                setPrice(price);
                setType(type);
                viewModel.addMenuItem(item);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mediator = (Mediator) context;
    }

    public int getMenuCount(ArrayList<MenuItem> items){
        int count = 0;
        for(int i = 0; i<items.size(); i++){
            count++;
        }
        return count;
    }

    public void setName(String name){
        viewModel.sName(name);
    }

    public void setPrice(float price){
        viewModel.sPrice(price);
    }
    public void setType(String type){
        viewModel.sType(type);
    }
}
