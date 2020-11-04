package com.example.recyclerviewdemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recyclerviewdemo.dao.PersonDAO;
import com.example.recyclerviewdemo.model.PersonAddress;
import com.example.recyclerviewdemo.model.PersonPhone;

@Database(entities = {PersonPhone.class, PersonAddress.class}, version = 2, exportSchema = false)
public abstract class PersonDataBase extends RoomDatabase {

    private static final String PERSON_DATA_BASE_NAME = "person_database";

    private static PersonDataBase instance;

    public abstract PersonDAO personDAO();

    public static PersonDataBase getDB(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, PersonDataBase.class, PERSON_DATA_BASE_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
