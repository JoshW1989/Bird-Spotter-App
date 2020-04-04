package com.example.birdspotter;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Queries for database
@Dao
public interface BirdDao {

    @Insert
    void insert(Bird bird);

    @Update
    void update(Bird bird);

    @Delete
    void delete(Bird bird);

    @Query("DELETE FROM bird_table")
    void deleteAllBirds();

    @Query("SELECT * FROM bird_table ORDER BY id DESC")
    LiveData<List<Bird>> getAllBirds();
}



