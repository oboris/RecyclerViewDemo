package com.example.recyclerviewdemo.model;

public class PersonPhone implements IPerson {
    private String name;
    private String phone;

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
