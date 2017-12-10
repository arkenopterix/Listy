package com.example.vrague.listy_phase1_testxml.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brieg on 11/30/17.
 */

public class GroceryList implements Serializable {


    private boolean was_updated;

    private int id;

    private String groceryListName;

    private List<GroceryItem> groceryItemList;

    public GroceryList(boolean was_updated, int id, String groceryListName, List<GroceryItem> itemCourses) {
        this.was_updated = was_updated;
        this.id = id;
        this.groceryListName = groceryListName;
        this.groceryItemList = itemCourses;
    }

    public GroceryList() {
        groceryItemList = new ArrayList<GroceryItem>() ;

    }


    public boolean isWas_updated() {
        return was_updated;
    }

    public void setWas_updated(boolean was_updated) {
        this.was_updated = was_updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GroceryItem> getGroceryItemList() {
        return groceryItemList;
    }

    public void setGroceryItemList(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    public void addGroceryItem( GroceryItem groceryItem) {
        this.getGroceryItemList().add(groceryItem);
    }



}
