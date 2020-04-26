package edu.scranton.nesbittj3.lunchilicious;
import java.util.ArrayList;

public class MenuItem {
    int id;
    CharSequence type;
    CharSequence name;
    String description;
    float unitPrice;
    public MenuItem(int id, CharSequence type, CharSequence name, String description, float unitPrice) {
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
