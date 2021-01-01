package com.example.cookbook;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DbItem.class}, version = 1, exportSchema = false)
public abstract class MealRoomDatabase extends RoomDatabase {

    public abstract MealDao mealDao();

    private static volatile MealRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MealRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MealRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MealRoomDatabase.class, "meal_database")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                MealDao dao = INSTANCE.mealDao();
                dao.deleteAll();

//                DbItem word = new Word("Hello");
//                dao.insert(word);
//                word = new Word("World");
//                dao.insert(word);
            });
        }
    };
}

