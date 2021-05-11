package com.example.myplaces.Models;

public class MyPlace {
    private String name;
    private String description;
    private String longitude;
    private String latitude;
    private int ID;


    public MyPlace(String nme,String desc)
    {
        this.setName(nme);
        this.setDescription(desc);
    }

    public MyPlace(String nme)
    {
        this(nme,"");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return this.name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
