package com.example.cookbook;

import android.app.Application;

import java.util.List;

class MealRepository {

    private MealDao mMealDao;
    private List<DbItem> mAllMeals;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    MealRepository(Application application) {
        MealRoomDatabase db = MealRoomDatabase.getDatabase(application);
        mMealDao = db.mealDao();
        mAllMeals = mMealDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    List<DbItem> getAllWords() {
        return mAllMeals;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(DbItem meal) {
        MealRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMealDao.insert(meal);
        });

    }

    void deleteAll(){
        MealRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMealDao.deleteAll();
        });
    }
}