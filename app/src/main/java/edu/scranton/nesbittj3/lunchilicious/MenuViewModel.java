package edu.scranton.nesbittj3.lunchilicious;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel {
    //tracks items list
    MutableLiveData<List<MenuItem>> menuLiveData = new MutableLiveData<>();
    List<MenuItem> items = new ArrayList<>();

        public LiveData<List<MenuItem>> getMenuItemsLiveData(){
            if(menuLiveData == null){
                menuLiveData = new MutableLiveData<>(items);
            }
            return menuLiveData;
        }

        public void addMenuItem(List<MenuItem> menuItems, int id, CharSequence type, CharSequence name, Float price){
            items = menuItems;
            items.add(new MenuItem (id, type, name, "", price));
            menuLiveData.setValue(items);

        }

}
