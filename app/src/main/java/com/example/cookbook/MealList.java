
package com.example.cookbook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealList {

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
