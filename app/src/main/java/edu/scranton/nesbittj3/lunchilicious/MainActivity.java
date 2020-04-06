package edu.scranton.nesbittj3.lunchilicious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import android.os.Bundle;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Mediator{

    private MenuFragmentAdapter adapter;
    private MenuFragment menuFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.container_main) != null) {
            Fragment menuFragment = MenuFragment.newInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_main, menuFragment, "MENU_FRAG")
                    .commitNow();
        }
        menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, menuFragment).commit();

    }

    public class MyViewModel extends ViewModel {
        ArrayList<MenuItem> items;
    }

    public class MenuItem {
        int id;
        String type;
        String name;
        String description;
        float unitPrice;
        public MenuItem(int id, String type, String name, String description, float unitPrice) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.description = description;
            this.unitPrice = unitPrice;
        }
    }

    public ArrayList<MenuItem> getMenuItems() {

        Bundle args = new Bundle();
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
        menuFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, menuFragment).commit();
        return items;
    }
}
