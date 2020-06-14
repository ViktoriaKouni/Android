package com.example.corona.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.corona.model.Tips;

@Database(entities = {Tips.class}, version = 1)
public abstract class TipsDatabase extends RoomDatabase {

    private static TipsDatabase instance;

    public abstract TipsDAO tipsDao();

    public static synchronized TipsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TipsDatabase.class, "tip_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}