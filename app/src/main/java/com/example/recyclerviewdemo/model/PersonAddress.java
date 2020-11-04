package com.example.recyclerviewdemo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_address")
public class PersonAddress implements IPerson{
    private String name;
    private String address;

    @PrimaryKey(autoGenerate = true)
    public int id;

    public PersonAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int getType() {
        return 2;
    }
}
