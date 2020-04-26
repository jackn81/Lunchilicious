package edu.scranton.nesbittj3.lunchilicious;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.RoomDatabase;
import edu.scranton.nesbittj3.lunchilicious.LunchiliciousDatabase;
import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends AndroidViewModel {

    LiveData<List<MenuItem>> menuLiveData = null;
    List<MenuItem> items = new ArrayList<>();

    public MenuViewModel(@NonNull Application application){
        super(application);
    }


        public LiveData<List<MenuItem>> getMenuItemsLiveData(){
            if(menuLiveData == null){
                Context context = getApplication().getApplicationContext();

                menuLiveData = LunchiliciousDatabase.getInstance(context).menuItemDao().getAllItems();
            }
            return menuLiveData;
        }

        public void addMenuItem(MenuItem menuItem){

            LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
                Context context = getApplication().getApplicationContext();
                int maxId = LunchiliciousDatabase.getInstance(context).menuItemDao().findMaxItemId();
                menuItem.id = maxId + 1;
                LunchiliciousDatabase.getInstance(context).menuItemDao().insertItem(menuItem);

            });
        }

}
