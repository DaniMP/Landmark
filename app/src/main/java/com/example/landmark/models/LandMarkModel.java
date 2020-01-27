package com.example.landmark.models;

public class LandMarkModel {

    public String name;
    public String description;
    public double latitude;
    public double longitude;

    public LandMarkModel(String name, String description, double latitude, double longitude){

        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
