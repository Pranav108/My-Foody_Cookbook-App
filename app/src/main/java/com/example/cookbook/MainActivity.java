package com.example.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private MealViewModel mWordViewModel;
    private ItemList list;
    private ArrayList<String> Catogery=new ArrayList<>();
    private RecyclerView recyclerView;
    private int check=0;
    private MenuItem favouriteItem;
    private ItemRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MealViewModel.class);

        getData("Beef");

        check=1;
    }

    private void getData(String catogery){
        Call<ItemList> mealList= RecipeApi.getService().getMealList(catogery);
        mealList.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                list= response.body();
                adapter=new ItemRecyclerViewAdapter(MainActivity.this,list.getItems());
                recyclerView.setAdapter(adapter);
//                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {

            }
        });
    }

    void filter(String text){

         RecipeApi.getService().getMealBySearch(text).enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                MealList mealList=new MealList();
                mealList=response.body();
                ArrayList<Item> item=new ArrayList<>();
                List<Meal> meals=mealList.getMeals();
                if(meals!=null){
                    for(Meal meal:meals){
                        String name=meal.getStrMeal();
                        String id=meal.getIdMeal();
                        String url=meal.getStrMealThumb();
                        item.add(new Item(id,name,url));
                    }

                    //update recyclerview
                    adapter.updateList(item);
                }else{
                    Toast.makeText(MainActivity.this,"Not Found",Toast.LENGTH_SHORT).show();
                    adapter.updateList(list.getItems());
                }

            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Not Found",Toast.LENGTH_SHORT).show();
                adapter.updateList(list.getItems());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchItem= menu.findItem(R.id.search);

        favouriteItem = menu.findItem(R.id.favourite);
        SearchView searchView=(SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query.toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    adapter.updateList(list.getItems());
                }
                return false;
            }
        });
        return true;
    }

    public void addIntoFav(DbItem meal){
        mWordViewModel.insert(meal);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.favourite){
            Intent intent = new Intent(MainActivity.this,FavouriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private String fillCatogery(){
        Catogery.add("Beef");
        Catogery.add("Breakfast");
        Catogery.add("Chicken");
        Catogery.add("Dessert");
        Catogery.add("Goat");
        Catogery.add("Lamb");
        Catogery.add("Miscellaneou");
        Catogery.add("Pasta");
        Catogery.add("Pork");
        Catogery.add("Seafood");
        Catogery.add("Side");
        Catogery.add("Starter");
        Catogery.add("Vegan");
        Catogery.add("Vegetarian");
        return Catogery.get(new Random().nextInt(10)) ;
    }

}