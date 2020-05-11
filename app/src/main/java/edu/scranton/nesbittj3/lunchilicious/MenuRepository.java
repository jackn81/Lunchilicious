package edu.scranton.nesbittj3.lunchilicious;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import androidx.lifecycle.LiveData;

import java.util.List;

import static java.lang.Boolean.TRUE;

public class MenuRepository {
    private MenuItemDao menuItemDao;
    private LiveData<List<MenuItem>> menuLiveData;

    public MenuRepository(Application application){
        LunchiliciousDatabase database = LunchiliciousDatabase.getInstance(application);
        menuItemDao = database.menuItemDao();
        menuLiveData = menuItemDao.getAllItems();
    }

    public void addMenuItem(MenuItem menuItem){
        new addMenuItemAsyncTask(menuItemDao).execute(menuItem);
        //menuItemDao.insertItem(menuItem);
        //new addMenuItemAsyncTask(menuItemDao).execute(menuItem);
    }

    public void updateMenuItems(List<MenuItem> menuItem){
        new updateMenuItemAsyncTask(menuItemDao).execute(menuItem);

        //new updateMenuItemAsyncTask(menuItemDao).execute(menuItem);
    }


    public  LiveData<List<MenuItem>> getMenuItems(){
        return menuItemDao.getAllItems();
    }

    private void sendToRemote(MenuItem menuItem){
        Log.d("MENUITEM", "SEND TO REMOTE: " + menuItem.toString());
    }

    private static class addMenuItemAsyncTask extends AsyncTask<MenuItem, Void, Void>{
        private MenuItemDao menuItemDao;

        private addMenuItemAsyncTask(MenuItemDao menuItemDao){
            this.menuItemDao = menuItemDao;
        }
        @Override
        protected Void doInBackground(MenuItem... items){
            menuItemDao.insertItem(items[0]);
            return null;
        }

    }
    private static class updateMenuItemAsyncTask extends AsyncTask<List<MenuItem>, Void, Void> {
        private MenuItemDao menuItemDao;

        private updateMenuItemAsyncTask(MenuItemDao menuItemDao) {
            this.menuItemDao = menuItemDao;
        }

        @Override
        protected Void doInBackground(List<MenuItem>... items) {
                menuItemDao.deleteAllItems();
                menuItemDao.insertList(items[0]);
            return null;
        }


    }
}
