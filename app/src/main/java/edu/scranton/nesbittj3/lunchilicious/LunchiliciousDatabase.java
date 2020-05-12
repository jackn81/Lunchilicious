package edu.scranton.nesbittj3.lunchilicious;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


            });
        }
    };

        private static ArrayList<MenuItem> getMenuItems() {

        return null;
    }
}
