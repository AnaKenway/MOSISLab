package com.example.myplaces.Models;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class MyPlace {
    public String name;
    public String description;
    public String longitude;
    public String latitude;
    @Exclude
    public String key;

    public MyPlace(){}


    public MyPlace(String nme,String desc)
    {
        name=nme;
        description=desc;
    }

    public MyPlace(String nme)
    {
        this(nme,"");
    }


    @Override
    public String toString(){
        return this.name;
    }

}
