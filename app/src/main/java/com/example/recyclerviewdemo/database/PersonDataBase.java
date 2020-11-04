package com.example.recyclerviewdemo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.recyclerviewdemo.dao.PersonDAO;
import com.example.recyclerviewdemo.model.PersonAddress;
import com.example.recyclerviewdemo.model.PersonPhone;

@Database(entities = {PersonPhone.class, PersonAddress.class}, version = 1)
public abstract class PersonDataBase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}
