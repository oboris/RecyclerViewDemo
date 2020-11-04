package com.example.recyclerviewdemo.database;

import android.content.Context;

import androidx.room.Room;

public class PersonDataBaseHelper {
    private static PersonDataBase instance;

    public static PersonDataBase getDB(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, PersonDataBase.class, "person_database").build();
        }
        return instance;
    }
}
