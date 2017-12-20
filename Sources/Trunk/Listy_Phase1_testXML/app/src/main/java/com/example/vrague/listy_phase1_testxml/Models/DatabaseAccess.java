package com.example.vrague.listy_phase1_testxml.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vrague.listy_phase1_testxml.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created by VRAGUE on 27/07/2017.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public void saveGroceryList(GroceryList groceryList) {
        //function that takes a groceryList as an input in order to save it in the database.

        //Decide whether the list must be inserted or updated
        if (groceryList.getId() == -1) {
            //list has no id, so it must be inserted
            insertGroceryList(groceryList);
        } else {
            //List has an id, so it already was inserted once. It must be updated
            updateGroceryList(groceryList);
        }
    }

    public void insertGroceryList(GroceryList groceryList) {
        //function that inserts a grocerylist item as well as it's item list

        //First insert the groceryList in order to get it's id
        long insertedListID = -1;

        ContentValues listValues = new ContentValues();
        listValues.put("list_name",groceryList.getGroceryListName());

        insertedListID = database.insert("GroceryList_table", null, listValues);



    }

    public void updateGroceryList(GroceryList groceryList) {


    }

    public long insertGroceryItemListLink(long groceryListID, long groceryItemID){
        //this function inserts a link between a groceryList and a grocery item

        long insertedLinkID = -1;

        //TODO insertlink
        return insertedLinkID;
    }

    public  long insertGroceryItem(GroceryItem itemCourse, long groceryListID){
        // Function that takes as an input the groceryItem to insert in the database, and the groceryListID of the list we wish to link it to.
        // returns: insertedID (value of the ID for the newly inserted object, and -1 if it failed.

        // initialize the return value to  -1
        long insertedID = -1;

        ContentValues values = new ContentValues();
        values.put("item_name",itemCourse.getNameItem());
        values.put("item_description", itemCourse.getDescriptionItem());
        values.put("item_quantity", itemCourse.getQuantityItem());
        values.put("item_unit", itemCourse.getUnitItem());
        insertedID = database.insert("GroceryItem_table", null, values);


        return insertedID;
    }

    public List<GroceryItem> getItemCourses(long groceryListID){
        // Function that retrieves all the groceryItems linked to the groceryListID given as an input
        // returns: an arrayList containing the groceryItems found in the database linked to the given id

        //Initialize return list
        List<GroceryItem> list = new ArrayList<>();

        //compose SQL query
        String sqlQuery = "SELECT * FROM GroceryItem_table WHERE  ";


        //Query database
        Cursor cursor = database.rawQuery(sqlQuery, null);

        //Iterate on the result to store the retrieved data in an ArrayList of GroceryItems
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GroceryItem itemCourse = new GroceryItem();
            itemCourse.setNameItem(cursor.getString(0));
            itemCourse.setDescriptionItem(cursor.getString(1));
            itemCourse.setQuantityItem(cursor.getString(2));
            itemCourse.setUnitItem(cursor.getString(3));
            list.add(itemCourse);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void updateItemCourse(GroceryItem oldItem, GroceryItem newItem){
        ContentValues values = new ContentValues();
        values.put("item_name", newItem.getNameItem());
        values.put("item_description", newItem.getDescriptionItem());
        values.put("item_quantity", newItem.getQuantityItem());
        values.put("item_unit", newItem.getUnitItem());
        database.update("ItemCourse",values, null, new String[] {oldItem.getNameItem()});
    }

    public void deleteItemCourse(GroceryItem itemCourse){
        database.delete("ItemCourse", "item_name=?", new String[]{itemCourse.getNameItem()});
        database.close();
    }

}
