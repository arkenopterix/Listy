package com.example.vrague.listy_phase1_testxml.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brieg on 11/30/17.
 */

public class GroceryList implements Serializable {


    private boolean was_updated;

    private long id;

    private String groceryListName;

    private List<GroceryItem> groceryItemList;

    public GroceryList(boolean was_updated, long id, String groceryListName, List<GroceryItem> itemCourses) {
        this.was_updated = was_updated;
        this.id = id;
        this.groceryListName = groceryListName;
        this.groceryItemList = itemCourses;
    }

    public GroceryList() {
        this.setGroceryItemList(new ArrayList<GroceryItem>());
        this.setId(-1);

    }


    public boolean isWas_updated() {
        return was_updated;
    }

    public void setWas_updated(boolean was_updated) {
        this.was_updated = was_updated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public GroceryItem getItemFromGroceryListByPosition (int pos) {

        GroceryItem groceryItem;

        groceryItem = this.getGroceryItemList().get(pos);

        return groceryItem;

    }

}
