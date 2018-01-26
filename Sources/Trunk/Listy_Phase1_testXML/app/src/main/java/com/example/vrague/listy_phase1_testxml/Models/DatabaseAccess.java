package com.example.vrague.listy_phase1_testxml.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.vrague.listy_phase1_testxml.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void saveGroceryItem(GroceryItem groceryItem, long listID){
        //function that takes a groceryList as an input in order to save it in the database.

        //Decide whether the item must be inserted or updated
        if (groceryItem.getId() == -1) {
            //list has no id, so it must be inserted
            insertGroceryItem(groceryItem);
        } else {
            //List has an id, so it already was inserted once. It must be updated
            updateGroceryItem(groceryItem);
        }

    }

    public void insertGroceryList(GroceryList groceryList) {
        //function that inserts a grocerylist item as well as it's item list

        //First insert the groceryList in order to get it's id
        long insertedListID = -1;

        ContentValues listValues = new ContentValues();
        listValues.put("list_name",groceryList.getGroceryListName());

        insertedListID = this.database.insert("GroceryList_table", null, listValues);

        //Add the retrieved ID to the groceryList object
        groceryList.setId(insertedListID);

        //insert or update the list items
        List<GroceryItem> itemsInTheList = groceryList.getGroceryItemList();

        Iterator<GroceryItem> groceryItemIterator = itemsInTheList.iterator();

        //Save the items in the grocerylist individually
        while (groceryItemIterator.hasNext()) {
            saveGroceryItem(groceryItemIterator.next(),insertedListID);
        }

    }

    public void updateGroceryList(GroceryList groceryList) {

        //Update the name of the groceryList (even if it was not changed)
        ContentValues values = new ContentValues();
        values.put("List_name", groceryList.getGroceryListName());
        this.database.update("GroceryList_table",values, "id = ?", new String[] {String.valueOf(groceryList.getId())});

        //Save the items in the grocerylist individually
        List<GroceryItem> itemsInTheList = groceryList.getGroceryItemList();

        Iterator<GroceryItem> groceryItemIterator = itemsInTheList.iterator();

        while (groceryItemIterator.hasNext()) {

            saveGroceryItem(groceryItemIterator.next(),groceryList.getId());
        }

    }

    public long insertGroceryItemListLink(long groceryListID, long groceryItemID){
        //this function inserts a link between a groceryList and a grocery item

        long insertedLinkID = -1;

        ContentValues values = new ContentValues();
        values.put("id_groceryList",String.valueOf(groceryListID));
        values.put("id_groceryItem",String.valueOf(groceryItemID));

        insertedLinkID = this.database.insert("GroceryItemListLinks_table", null, values);

        return insertedLinkID;
    }

    public long insertGroceryItem(GroceryItem itemCourse){
        // Function that takes as an input the groceryItem to insert in the database, and the groceryListID of the list we wish to link it to.
        // returns: insertedID (value of the ID for the newly inserted object, and -1 if it failed.

        // initialize the return value to  -1
        long insertedID = -1;

        ContentValues values = new ContentValues();
        values.put("item_name",itemCourse.getNameItem());
        values.put("item_description", itemCourse.getDescriptionItem());
        values.put("item_quantity", itemCourse.getQuantityItem());
        values.put("item_unit", itemCourse.getUnitItem());
        insertedID = this.database.insert("GroceryItem_table", null, values);


        return insertedID;
    }

    public List<GroceryItem> getItemCourses(long groceryListID){
        // Function that retrieves all the groceryItems linked to the groceryListID given as an input
        // returns: an arrayList containing the groceryItems found in the database linked to the given id

        //Initialize return list
        List<GroceryItem> list = new ArrayList<>();

        //build secure sql query
        //SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //qb.setTables("GroceryItem_table");


        //compose SQL query
        String sqlQuery = "SELECT *  FROM GroceryItem_table WHERE GroceryItem_table.id IN (SELECT GroceryItemListLinks_table.id_groceryItem FROM GroceryItemListLinks_table WHERE GroceryItemListLinks_table.id_groceryList = '";
        sqlQuery.concat(String.valueOf(groceryListID));
        sqlQuery.concat("'); ");

        //Query database
        Cursor cursor = this.database.rawQuery(sqlQuery, null);

        //Iterate on the result to store the retrieved data in an ArrayList of GroceryItems
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GroceryItem itemCourse = new GroceryItem();
            itemCourse.setId(Integer.getInteger(cursor.getString(0)));
            itemCourse.setNameItem(cursor.getString(1));
            itemCourse.setDescriptionItem(cursor.getString(2));
            itemCourse.setQuantityItem(cursor.getString(3));
            itemCourse.setUnitItem(cursor.getString(4));
            itemCourse.setIs_favorite(Boolean.getBoolean(cursor.getString(5)));
            list.add(itemCourse);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void updateGroceryItem(GroceryItem ItemToUpdate){
        ContentValues values = new ContentValues();
        values.put("item_name", ItemToUpdate.getNameItem());
        values.put("item_description", ItemToUpdate.getDescriptionItem());
        values.put("item_quantity", ItemToUpdate.getQuantityItem());
        values.put("item_unit", ItemToUpdate.getUnitItem());
        values.put("item_isFav", ItemToUpdate.is_favorite());
        this.database.update("GroceryItem_table",values, "id = ?", new String[] {String.valueOf(ItemToUpdate.getId())});

    }

    public void deleteItemCourse(GroceryItem itemCourse){
        this.database.delete("GroceryItem_table", "id=?", new String[]{String.valueOf(itemCourse.getId())});
        this.database.close();
    }

    public boolean checkIfLinkExist (long idList, long iditem) {

        boolean linkExists = false;

        return linkExists;

    }
}
