package com.example.cookbook;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RecipeApi {

//    private static final String key="1";
    private static final String url="https://www.themealdb.com/api/json/v1/1/";

    public static MealService mealService=null;

    public static MealService getService(){

        if (mealService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mealService=retrofit.create(MealService.class);
        }
        return mealService;
    }

    public interface MealService{

        @GET("filter.php?")
        Call<ItemList> getMealList(@Query("i") String category);

        @GET("lookup.php?")
        Call<MealList> getMealById(@Query("i") String id);

        @GET("search.php?")
        Call<MealList> getMealBySearch(@Query("s") String name);
    }
}
