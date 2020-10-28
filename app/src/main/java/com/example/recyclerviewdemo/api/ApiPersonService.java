package com.example.recyclerviewdemo.api;

import com.example.recyclerviewdemo.model.CompositePerson;

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiPersonService {

    private static final String HOST = "http://boris.cdu.edu.ua/";

    private static ApiPersonService instance;

    private final PersonService personService;

    private ApiPersonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        personService = retrofit.create(PersonService.class);
    }

    public synchronized static ApiPersonService get() {
        if (instance == null) {
            instance = new ApiPersonService();
        }
        return instance;
    }

    public List<CompositePerson> getPersons(Callback<List<CompositePerson>> callback) {

        if (null == callback) {
            try {
                return personService.getPersons().execute().body();
            } catch (IOException e) {
                return null;
            }
        } else {
            personService.getPersons().enqueue(callback);
            return null;
        }
    }
}
