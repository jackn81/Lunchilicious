package edu.scranton.nesbittj3.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MenuItemDao {

    @Insert
    void insertItem(MenuItem menuItem);

    @Insert
    void insertList(List<MenuItem> menuItems);

    @Update
    void updateItem(MenuItem menuItem);

    @Delete
    void deleteItem(MenuItem menuItem);

    @Query("DELETE FROM item")
    void deleteAllItems();

    @Query("SELECT * FROM item")
    LiveData<List<MenuItem>> getAllItems();

    @Query("SELECT max(id) FROM item")
    int findMaxItemId();
}
