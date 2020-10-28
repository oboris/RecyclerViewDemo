package com.example.recyclerviewdemo.api;

import com.example.recyclerviewdemo.model.CompositePerson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonService {

    @GET("infoModel.json")
    Call<List<CompositePerson>> getPersons();
}
