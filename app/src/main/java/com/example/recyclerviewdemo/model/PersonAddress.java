package com.example.recyclerviewdemo.model;

public class PersonAddress implements IPerson{
    private String name;
    private String address;

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
