package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = MenuItem.class, version = 6)
public abstract class LunchiliciousDatabase extends RoomDatabase {

    private static LunchiliciousDatabase instance;
    private static final int NUM_THREADS = 1;

    public static ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUM_THREADS);

    public abstract MenuItemDao menuItemDao();

    public static synchronized LunchiliciousDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LunchiliciousDatabase.class, "lunchilcious_database")
                    .fallbackToDestructiveMigration().addCallback(lunchDBCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback lunchDBCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);

            databaseWriteExecutor.execute( () -> {
               MenuItemDao menuItemDao = instance.menuItemDao();
               menuItemDao.deleteAllItems();
                List<MenuItem> menuItems = getMenuItems();
                menuItemDao.insertList(menuItems);
            });
        }
    };

        private static ArrayList<MenuItem> getMenuItems() {

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem( "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem( "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem( "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem( "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem( "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
        items.add(new MenuItem( "Pizza", "Plain", "cheese and tomato", (float) 9.50));
        items.add(new MenuItem( "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
        items.add(new MenuItem( "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
        items.add(new MenuItem( "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
        items.add(new MenuItem( "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
        items.add(new MenuItem( "Side", "Fries", "large hot fries", (float) 2.95));
        items.add(new MenuItem( "Side", "Gravy Fries",  "Fries with gravy on top", (float) 3.95));
        items.add(new MenuItem( "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
        items.add(new MenuItem( "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
        items.add(new MenuItem( "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));

        return items;
    }
}
