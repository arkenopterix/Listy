package com.example.vrague.listy_phase1_testxml.Models;

import java.io.Serializable;

/**
 * Created by VRAGUE on 27/07/2017.
 */

public class GroceryItem implements Serializable {


    private boolean was_updated;
    private int id;
    private String nameItem;
    private String descriptionItem;
    private String quantityItem;
    private String unitItem;
    private boolean is_favorite;

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

    public boolean is_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

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
