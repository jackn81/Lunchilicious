package edu.scranton.nesbittj3.lunchilicious;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MenuViewModel extends ViewModel {
    //tracks items list


    String type;

    int count;
    private MenuItem item = new MenuItem(0, "", "", "", 0);
    MutableLiveData<MenuItem> menuLiveData;
        public LiveData<MenuItem> getMenuItemsLiveData(){
            if(menuLiveData == null){
                menuLiveData = new MutableLiveData<>(item);
            }
            return menuLiveData;
        }

        public void addMenuItem(MenuItem item){
            menuLiveData.setValue(item);
        }

        public void sName(String name){
            item.name = name;
        }

         public void sPrice(float price){
            item.unitPrice = price;
        }

        public void sType(String type){
            item.type = type;
        }

}
