package com.example.vrague.listy_phase1_testxml.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vrague.listy_phase1_testxml.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public  void insertItemCourse(ItemCourse itemCourse){
        ContentValues values = new ContentValues();
        values.put("item_name",itemCourse.getNameItem());
        values.put("item_description", itemCourse.getDescriptionItem());
        values.put("item_quantity", itemCourse.getQuantityItem());
        values.put("item_unit", itemCourse.getUnitItem());
        database.insert("ItemCourse", null, values);
    }

    public List<ItemCourse> getItemCourses(){
        List<ItemCourse> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ItemCourse", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ItemCourse itemCourse = new ItemCourse();
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

    public void updateItemCourse(ItemCourse oldItem, ItemCourse newItem){
        ContentValues values = new ContentValues();
        values.put("item_name", newItem.getNameItem());
        values.put("item_description", newItem.getDescriptionItem());
        values.put("item_quantity", newItem.getQuantityItem());
        values.put("item_unit", newItem.getUnitItem());
        database.update("ItemCourse",values, null, new String[] {oldItem.getNameItem()});
    }

    public void deleteItemCourse(ItemCourse itemCourse){
        database.delete("ItemCourse", "item_name=?", new String[]{itemCourse.getNameItem()});
        database.close();
    }

}
