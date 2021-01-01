package com.example.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.meal_img)
    ImageView meal_img;

    @BindView(R.id.meal_name)
    TextView meal_name;

    @BindView(R.id.instructions_tv)
    TextView instruction_tv;

    @BindView(R.id.ingredient_rv)
    RecyclerView ingredients_rv;

    private String mealId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mealId=intent.getStringExtra("meal_id");
        ingredients_rv.setLayoutManager(new LinearLayoutManager(this));
        getDetailData();
    }

    public void getDetailData(){
        Call<MealList> meal=RecipeApi.getService().getMealById(mealId);
        meal.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                MealList meal1=response.body();
                Meal currMeal=meal1.getMeals().get(0);

                Glide.with(DetailActivity.this)
                        .load(currMeal.getStrMealThumb())
                        .centerCrop()
                        .placeholder(R.drawable.dinner)
                        .into(meal_img);

                meal_name.setText(currMeal.getStrMeal());
                instruction_tv.setText(currMeal.getStrInstructions());

                List<String> ingredients=  currMeal.getListOfIngredients();
                List<String> measurements= currMeal.getMeasurmentsList();
                ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
                for(int i=0;i< ingredients.size();i++){
                    if (ingredients.get(i)!=null && !ingredients.get(i).isEmpty()){
                        ArrayList<String> list = new ArrayList<>();
                        list.add(ingredients.get(i));
                        list.add(measurements.get(i));
                        data.add(list);
                    }
                }
                ingredients_rv.setAdapter(new IngredientRecyclerViewAdapter(data,DetailActivity.this));
//                Toast.makeText(DetailActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {

            }
        });
    }
}