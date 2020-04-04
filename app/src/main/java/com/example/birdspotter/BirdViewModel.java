package com.example.birdspotter;

import androidx.lifecycle.AndroidViewModel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;


// View model calls functions from the repository (this prevents calling them from the activity,
// which is better for clean abstraction)
public class BirdViewModel extends AndroidViewModel {
    private BirdRepository repository;
    private LiveData<List<Bird>> allBirds;

    public BirdViewModel(@NonNull Application application) {
        super(application);
        repository = new BirdRepository(application);
        allBirds = repository.getAllBirds();
    }

    public void insert(Bird bird) {
        repository.insert(bird);
    }

    public void update(Bird bird) {
        repository.update(bird);
    }

    public void delete(Bird bird) {
        repository.delete(bird);
    }

    public void deleteAllBirds() {
        repository.deleteAllBirds();
    }

    public LiveData<List<Bird>> getAllBirds() {
        return allBirds;
    }
}
