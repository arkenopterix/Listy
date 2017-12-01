package com.example.vrague.listy_phase1_testxml.Models;

import java.util.List;

/**
 * Created by brieg on 11/30/17.
 */

public class GroceryList {



    private String groceryListName;

    private List<GroceryItem> itemCourses;

    public List<GroceryItem> getItemCourses() {
        return itemCourses;
    }

    public void setItemCourses(List<GroceryItem> itemCourses) {
        this.itemCourses = itemCourses;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }



}
