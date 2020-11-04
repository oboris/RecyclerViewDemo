package com.example.recyclerviewdemo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recyclerviewdemo.model.PersonAddress;
import com.example.recyclerviewdemo.model.PersonPhone;

import java.util.List;

@Dao
public interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPersonAddress(PersonAddress personAddress);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPersonPhone(PersonPhone personPhone);

    @Query("select * from person_address")
    List<PersonAddress> getPersonAddressFromBD();

    @Query("select * from person_phones")
    List<PersonPhone> getPersonPhoneFromBD();
}
