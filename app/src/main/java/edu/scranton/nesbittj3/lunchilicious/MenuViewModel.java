package edu.scranton.nesbittj3.lunchilicious;


import android.app.Application;
import android.content.Context;
import android.view.Menu;

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
    MenuRepository repository;

    public MenuViewModel(@NonNull Application application){
        super(application);
        repository = new MenuRepository(application);
        menuLiveData = repository.getMenuItems();
    }


        public LiveData<List<MenuItem>> getMenuItemsLiveData(){
            if(menuLiveData == null){
                Context context = getApplication().getApplicationContext();

                menuLiveData = LunchiliciousDatabase.getInstance(context).menuItemDao().getAllItems();
            }
            return menuLiveData;
        }

        public void addMenuItem(MenuItem menuItem){
            repository.addMenuItem(menuItem);
        }

        public void updateMenuItems(List<MenuItem> menuItem) {
            repository.updateMenuItems(menuItem);

        }
}
