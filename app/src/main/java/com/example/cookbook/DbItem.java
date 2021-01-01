package com.example.cookbook;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class DbItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String idMeal;

    @NonNull
    @ColumnInfo(name = "name")
    private String strMeal;

    @NonNull
    @ColumnInfo(name = "image")
    private String strMealThumb;



    public DbItem(@NonNull String idMeal, @NonNull String strMeal, @NonNull String strMealThumb) {
        this.strMeal=strMeal;
        this.idMeal=idMeal;
        this.strMealThumb=strMealThumb;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
}