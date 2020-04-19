package edu.scranton.nesbittj3.lunchilicious;
import java.util.ArrayList;

public class MenuItem {
    int id;
    String type;
    String name;
    String description;
    float unitPrice;
    public MenuItem(int id, String type, String name, String description, float unitPrice) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
    }


}
