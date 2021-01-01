package com.example.cookbook;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private MealViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    MenuItem deleteAll;
    RecyclerView recyclerView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        textView=findViewById(R.id.empty_tv);

        mWordViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MealViewModel.class);

        recyclerView = findViewById(R.id.fav_rv);
        recyclerView.setVisibility(View.VISIBLE);
        List<DbItem> items=new LinkedList<>();
        items = mWordViewModel.getAllWords();

        if (items.size()>0){
            textView.setVisibility(View.GONE);
        }
        final FavouriteRecyclerViewAdapter adapter = new FavouriteRecyclerViewAdapter(FavouriteActivity.this,items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.delete_menu,menu);
        deleteAll = menu.findItem(R.id.delete_all);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
            mWordViewModel.deleteAll();
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            Toast.makeText(FavouriteActivity.this,"Favourite Items Deleted Successfully",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}