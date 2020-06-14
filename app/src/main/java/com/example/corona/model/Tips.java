package com.example.corona.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tips_table")
public class Tips {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;


    public Tips(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}