package com.example.birdspotter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bird_table")
public class Bird {

    // Database attributes
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    private String date;

    private String location;

    public Bird(String name, String description, String date, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // Setter for Primary Key
    public void setId(int id) {
        this.id = id;
    }

    // Getters for all fields
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }


}
