package com.example.vrague.listy_phase1_testxml;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by VRAGUE on 27/07/2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "itemCourse.db";
    private static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE ItemCourse(item_name TEXT PRIMARY KEY, item_description TEXT, item_quantity TEXT, item_unit TEXT);");
    }

    //A implémenter en cas de modification de la BDD
    @Override
    public void onUpgrade(SQLiteDatabase db ,int oldVersion, int newVersion){

    }



}
