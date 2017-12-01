package com.example.vrague.listy_phase1_testxml;

/**
 * Created by VRAGUE on 26/07/2017.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.vrague.listy_phase1_testxml.Models.DatabaseAccess;
import com.example.vrague.listy_phase1_testxml.Models.GroceryItem;


public class FavoriteActivity extends AppCompatActivity  {

    private ListView favoriteListView;
    private String[] favItemList;
    private Button fav_btnReturn;
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_item);

        findFavoriteItem();
    }

    private void findFavoriteItem(){

        findGUI();
        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        loadFavItem();

        this.fav_btnReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                backToMainActivity();
            }
        });

        this.favoriteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addItemToList(position);
            }
        });
    }

    private void findGUI(){
        //Find the GUI component
        this.favoriteListView = (ListView) findViewById(R.id.fav_lvFavItem);
        this.fav_btnReturn = (Button) findViewById(R.id.fav_btnReturn);
    }

    private void loadFavItem(){
        //Create the Array
        this.favItemList = getResources().getStringArray(R.array.fav_item_name);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favItemList);
        favoriteListView.setAdapter(adapter);

    }

    private void addItemToList(int index){
        databaseAccess.open();
        GroceryItem item = new GroceryItem();
        item.setNameItem(favItemList[index]);

        databaseAccess.insertItemCourse(item);
        databaseAccess.close();
        this.finish();
    }





    private void backToMainActivity(){
        this.finish();
    }

}
