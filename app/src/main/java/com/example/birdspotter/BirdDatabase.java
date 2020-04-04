package com.example.birdspotter;

import android.content.Context;
import android.icu.text.CaseMap;
import android.os.AsyncTask;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Ties the Bird Dao to the Bird class and creates database
@Database(entities = {Bird.class}, version = 1)
public abstract class BirdDatabase extends RoomDatabase {

    private static BirdDatabase instance;

    public abstract BirdDao birdDao();

    public static synchronized BirdDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BirdDatabase.class, "bird_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // Initially populates the database with some data
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BirdDao birdDao;

        private PopulateDbAsyncTask(BirdDatabase db) {
            birdDao = db.birdDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            birdDao.insert(new Bird("Pigeon1", "Description 1", "01/08/1989", "Glasgow1"));
            birdDao.insert(new Bird("Pigeon2", "Description 2", "02/08/1989", "Glasgow2"));
            birdDao.insert(new Bird("Pigeon3", "Description 3", "03/08/1989", "Glasgow3"));
            return null;
        }
    }

}
