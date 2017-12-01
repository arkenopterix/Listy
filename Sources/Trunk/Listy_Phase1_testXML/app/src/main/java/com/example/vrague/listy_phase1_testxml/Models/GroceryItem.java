package com.example.vrague.listy_phase1_testxml.Models;

import java.io.Serializable;

/**
 * Created by VRAGUE on 27/07/2017.
 */

public class GroceryItem implements Serializable {
    private String nameItem;
    private String descriptionItem;
    private String quantityItem;
    private String unitItem;

    public String getNameItem(){
        return nameItem;
    }

    public String getDescriptionItem(){
        return descriptionItem;
    }

    public String getQuantityItem(){
        return quantityItem;
    }

    public String getUnitItem(){
        return unitItem;
    }

    public void setNameItem(String nameItem){
        this.nameItem = nameItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    public void setQuantityItem(String quantityItem) {
        this.quantityItem = quantityItem;
    }

    public  void setUnitItem(String unitItem){
        this.unitItem = unitItem;
    }

    @Override
    public String toString(){
        return nameItem + " " + descriptionItem;
    }





}
