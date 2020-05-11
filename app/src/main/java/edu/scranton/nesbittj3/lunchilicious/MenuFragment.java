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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuFragmentAdapter adapter;
    private List<MenuItem> menuItems;
    private MenuViewModel viewModel;
    private Button add;
    private Button update;
    private TextView inName;
    private TextView inPrice;
    private TextView inType;
    private KeplerPlaceHolderAPI client;


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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://aristotle.cs.scranton.edu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(KeplerPlaceHolderAPI.class);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));

        adapter = new MenuFragmentAdapter(context);
        recyclerView.setAdapter(adapter);
        showMenu(null);
        add = view.findViewById(R.id.add);
        update = view.findViewById(R.id.update);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addToMenu(view);
            }
        });
        update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                updateMenu(view);
            }
        });



        return view;
    }


    public void showMenu(View v){
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        adapter = new MenuFragmentAdapter(getContext());
        recyclerView.setAdapter(adapter);
        viewModel.getMenuItemsLiveData().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems){
                adapter.setMenuItems(menuItems);
                //adapter.notifyDataSetChanged();
            }
        });
    }

    public void addToMenu(View v){
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        String name = inName.getText().toString();
        String p = inPrice.getText().toString();
        float price = Float.parseFloat(p);
        String type = inType.getText().toString();
        int id = 0;
        MenuItem menuItem = new MenuItem(type, name, "", price);
        //viewModel.addMenuItem(menuItem);
        Call<MenuItem> call = client.addToMenu(menuItem);
        call.enqueue(new Callback<MenuItem>() {
           @Override
           public void onResponse(Call<MenuItem> call, Response<MenuItem> response){
               if(!response.isSuccessful()){

               }
               MenuItem menuResponse = response.body();
               Toast.makeText(getContext(), menuResponse.getName(), Toast.LENGTH_SHORT).show();
           }

           @Override
            public void onFailure(Call<MenuItem> call, Throwable t){

           }
        });

    }

    public void updateMenu(View v){
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        Call<List<MenuItem>> call = client.updateMenu();
        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response){
               if(!response.isSuccessful()){
                   Toast.makeText(getContext(), "Code: " + response.code(),
                           Toast.LENGTH_LONG).show();
               }
                List<MenuItem> menuResponse = response.body();
                viewModel.updateMenuItems(menuResponse);

            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t){
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



}
