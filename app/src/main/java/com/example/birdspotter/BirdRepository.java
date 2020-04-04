package com.example.birdspotter;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

// Repository interacts with the BirdDao queries, executing commands using an ASyncTask
public class BirdRepository {
    private BirdDao birdDao;
    private LiveData<List<Bird>> allBirds;

    public BirdRepository(Application application) {
        BirdDatabase database = BirdDatabase.getInstance(application);
        birdDao = database.birdDao();
        allBirds = birdDao.getAllBirds();
    }

    public void insert(Bird bird) {
        new InsertBirdAsyncTask(birdDao).execute(bird);
    }

    public void update(Bird bird) {
        new UpdateBirdAsyncTask(birdDao).execute(bird);
    }

    public void delete(Bird bird) {
        new DeleteBirdAsyncTask(birdDao).execute(bird);
    }

    public void deleteAllBirds() {
        new DeleteAllBirdsAsyncTask(birdDao).execute();
    }

    public LiveData<List<Bird>> getAllBirds() {
        return allBirds;
    }

    private static class InsertBirdAsyncTask extends AsyncTask<Bird, Void, Void> {
        private BirdDao birdDao;

        private InsertBirdAsyncTask(BirdDao birdDao) {
            this.birdDao = birdDao;
        }

        @Override
        protected Void doInBackground(Bird... birds) {
            birdDao.insert(birds[0]);
            return null;
        }
    }

    private static class UpdateBirdAsyncTask extends AsyncTask<Bird, Void, Void> {
        private BirdDao birdDao;

        private UpdateBirdAsyncTask(BirdDao birdDao) {
            this.birdDao = birdDao;
        }

        @Override
        protected Void doInBackground(Bird... birds) {
            birdDao.update(birds[0]);
            return null;
        }
    }

    private static class DeleteBirdAsyncTask extends AsyncTask<Bird, Void, Void> {
        private BirdDao birdDao;

        private DeleteBirdAsyncTask(BirdDao birdDao) {
            this.birdDao = birdDao;
        }

        @Override
        protected Void doInBackground(Bird... birds) {
            birdDao.delete(birds[0]);
            return null;
        }
    }

    private static class DeleteAllBirdsAsyncTask extends AsyncTask<Void, Void, Void> {
        private BirdDao birdDao;

        private DeleteAllBirdsAsyncTask(BirdDao birdDao) {
            this.birdDao = birdDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            birdDao.deleteAllBirds();
            return null;
        }
    }
}
