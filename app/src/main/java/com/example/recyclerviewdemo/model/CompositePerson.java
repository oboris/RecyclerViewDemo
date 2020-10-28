package com.example.recyclerviewdemo.model;

import com.google.gson.annotations.SerializedName;

public class CompositePerson {

    private final String name;

    private final String phone;

    @SerializedName("info")
    private final String address;

    private final int type;

    public CompositePerson(String name, String phone, String address, int type) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getType() {
        return type;
    }

    public IPerson toIPerson(){
        switch (getType()){
            case 1:
                return new PersonPhone(getName(), getPhone());
            case 2:
                return new PersonAddress(getName(), getAddress());
        }
        return null;
    }
}
