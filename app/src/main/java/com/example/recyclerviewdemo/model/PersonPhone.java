package com.example.recyclerviewdemo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_phones")
public class PersonPhone implements IPerson {
    private String name;
    private String phone;

    @PrimaryKey(autoGenerate = true)
    public int id;

    public PersonPhone(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int getType() {
        return 1;
    }
}
