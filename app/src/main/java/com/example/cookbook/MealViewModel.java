package com.example.cookbook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository mRepository;

    private final List<DbItem> mAllMeals;

    public MealViewModel (Application application) {
        super(application);
        mRepository = new MealRepository(application);
        mAllMeals = mRepository.getAllWords();
    }

    List<DbItem> getAllWords() { return mAllMeals; }

    public void insert(DbItem meal) { mRepository.insert(meal); }

    public void deleteAll(){
        mRepository.deleteAll();
    }
}