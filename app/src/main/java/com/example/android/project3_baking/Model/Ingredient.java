package com.example.android.project3_baking.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ingredient implements Serializable{

    int quantity;
    String measure;
    String ingredient;

    public Ingredient(){

    }

    public Ingredient(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}
