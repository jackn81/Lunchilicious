package edu.scranton.nesbittj3.lunchilicious;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "item")
public class MenuItem {
    @PrimaryKey
    public int id;
    public String type;
    public String name;
    public String description;
    public float unitPrice;

    public MenuItem(int id, String type, String name, String description, float unitPrice) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
    }


    public int getId(){
        return id;
    }
    public CharSequence getType(){
        return type;
    }
    public CharSequence getName(){
        return name;
    }
    public Float getPrice(){
        return unitPrice;
    }



}
