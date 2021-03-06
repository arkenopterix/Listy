package com.example.vrague.listy_phase1_testxml;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vrague.listy_phase1_testxml.Models.DatabaseAccess;
import com.example.vrague.listy_phase1_testxml.Models.ItemCourse;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView main_ListView;
    private String[] default_startList;
    private List<ItemCourse> itemCourses;
    private DatabaseAccess databaseAccess;

    private Button main_btnAdd;
    private Button main_btnAddFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findGui();
        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        //loadDefaultArray();

        //addTest();
        //updateListView();

        //Set event listener to Button
        this.main_btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                viewFav();
            }
        });

        this.main_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        this.main_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editItem(position);
            }
        });

    }

    private void findGui(){
        //Find the GUI component
        this.main_ListView = (ListView) findViewById(R.id.main_listViewItem);
        this.main_btnAdd = (Button) findViewById(R.id.main_btnAdd);
        this.main_btnAddFav = (Button) findViewById(R.id.main_btnAddFav);
    }

    private void addTest(){
        databaseAccess.open();
        ItemCourse newItem = new ItemCourse();
        newItem.setNameItem("TEst");
        newItem.setDescriptionItem(("la bbd marche"));
        newItem.setQuantityItem("defrf");
        newItem.setUnitItem("ededd");

        databaseAccess.insertItemCourse(newItem);
        databaseAccess.close();
    }

    private List<ItemCourse> getItemCourses(){
        databaseAccess.open();
        List<ItemCourse> list = databaseAccess.getItemCourses();
        databaseAccess.close();
        return list;
    }

    private void updateListView(){
        this.itemCourses = getItemCourses();

        //Create the custom adapter
        ItemCourseAdapter adapter = new ItemCourseAdapter(this, itemCourses);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemCourses);
        this.main_ListView.setAdapter(adapter);
    }

    private void loadDefaultArray(){
        //Create the Array
        this.default_startList = getResources().getStringArray(R.array.item_default);

        //Create an Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,default_startList);
        main_ListView.setAdapter(adapter);

    }

    @Override
    protected void onResume(){
        super.onResume();
        updateListView();
    }


    private void addItem (){
        Intent intent = new Intent(this, EditItemActivity.class);
        startActivity(intent);
    }


    private void viewFav () {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    private void editItem(int index){
        ItemCourse item = itemCourses.get(index);
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("ITEMCOURSE", item);
        startActivity(intent);
    }



    private class ItemCourseAdapter extends ArrayAdapter<ItemCourse>{

        public ItemCourseAdapter(Context context, List<ItemCourse> objects){
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.list_itemcourse_layout, parent, false);
            }
            TextView txtName = (TextView) convertView.findViewById(R.id.list_nameItem);
            TextView txtQuantity = (TextView) convertView.findViewById(R.id.list_quantityItem);
            TextView txtUnit = (TextView) convertView.findViewById(R.id.list_unitItem);
            ItemCourse itemCourse = itemCourses.get(position);
            txtName.setText(itemCourse.getNameItem());
            txtQuantity.setText(itemCourse.getQuantityItem());
            txtUnit.setText(itemCourse.getUnitItem());
            return convertView;
        }
    }
}
