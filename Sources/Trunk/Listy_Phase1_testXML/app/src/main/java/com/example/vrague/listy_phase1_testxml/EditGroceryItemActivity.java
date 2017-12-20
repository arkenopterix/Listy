package com.example.vrague.listy_phase1_testxml;

/**
 * Created by VRAGUE on 26/07/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vrague.listy_phase1_testxml.Models.DatabaseAccess;
import com.example.vrague.listy_phase1_testxml.Models.GroceryItem;


public class EditGroceryItemActivity extends AppCompatActivity {

    private Button edit_btnValidation;
    private Button edit_btnCancel;
    private Button edit_btnAddFav;

    private EditText edit_etNameItem;
    private EditText edit_etDescription;
    private EditText edit_etQuantity;
    private EditText edit_etUnit;

    private GroceryItem item;
    private DatabaseAccess databaseAccess;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item_edit);

        findGUI();
        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        checkIntentForContact();

        this.edit_btnValidation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createNewItem();
            }
        });

        this.edit_btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFav();
            }
        });

        this.edit_btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });
    }


    private void findGUI(){
        this.edit_btnValidation = (Button) findViewById(R.id.edit_btnValidation);
        this.edit_btnCancel = (Button) findViewById(R.id.edit_btnCancel);
        this.edit_btnAddFav = (Button) findViewById(R.id.edit_btnAddFav);

        this.edit_etNameItem = (EditText) findViewById(R.id.edit_etNameItem);
        this.edit_etDescription = (EditText) findViewById(R.id.edit_etDescription);
        this.edit_etQuantity = (EditText) findViewById(R.id.edit_etQuantity);
        this.edit_etUnit = (EditText) findViewById(R.id.edit_etUnit);
    }

    private void checkIntentForContact(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            item = (GroceryItem) bundle.get("ITEMCOURSE");
            if(item !=null){
                this.edit_etNameItem.setText(item.getNameItem());
                this.edit_etDescription.setText(item.getDescriptionItem());
                this.edit_etQuantity.setText(item.getQuantityItem());
                this.edit_etUnit.setText(item.getUnitItem());
            }
        }
    }


    private void createNewItem(){
        databaseAccess.open();
        GroceryItem newItem = new GroceryItem();
        newItem.setNameItem(edit_etNameItem.getText().toString());
        newItem.setDescriptionItem((edit_etDescription.getText().toString()));
        newItem.setQuantityItem(edit_etQuantity.getText().toString());
        newItem.setUnitItem(edit_etUnit.getText().toString());

        databaseAccess.insertGroceryItem(newItem);
        databaseAccess.close();

        this.finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);

    }

    private void returnToMain(){

        this.finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    private void createNewFav(){
        Toast.makeText(EditGroceryItemActivity.this, "The favorite item has been add to the list", Toast.LENGTH_SHORT).show();
    }

}
