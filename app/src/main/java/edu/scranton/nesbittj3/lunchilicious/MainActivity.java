package edu.scranton.nesbittj3.lunchilicious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.container_main) != null) {
            Fragment menuFragment = MenuFragment.newInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_main, menuFragment, "MENU_FRAG")
                    .commitNow();
        }

    }

}