package com.example.corona.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.corona.model.Tips;

import java.util.List;


@Dao
public interface TipsDAO {

    @Insert
    void insert(Tips tips);

    @Update
    void update(Tips tips);

    @Delete
    void delete(Tips tips);

    @Query("DELETE FROM tips_table")
    void deleteAllTips();

    @Query("SELECT * FROM tips_table ")
    LiveData<List<Tips>> getAllTips();

}

