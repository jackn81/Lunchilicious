package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;



public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuFragmentAdapter adapter;
    private Mediator mediator;
    private ArrayList<MainActivity.MenuItem> menu = new ArrayList<>();
    private MainActivity.MyViewModel viewModel;
    private TextView textView;
    public static MenuFragment newInstance(){
        return new MenuFragment();
    }

    public MenuFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //viewModel = new ViewModelProvider(this.getActivity()).get(MainActivity.MyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //viewModel = new ViewModelProvider(this.getActivity()).get(MainActivity.MyViewModel.class);
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        menu = mediator.getMenuItems();
        adapter = new MenuFragmentAdapter(this.getActivity(), menu, textView);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mediator = (Mediator) context;
    }



}
