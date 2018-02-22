package com.example.weibinwang.myapplication.Bean;

import com.example.weibinwang.myapplication.Common.Classify.TypeRestaurant;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class Restaurant {
    private int id;
    private String address;
    private String description;
    private TypeRestaurant type;
    private String contact;

    private Restaurant(){
        //do nothing, just wait for set() methods
    }

    /*
    * The part of get() methods.
    * */
    public int getId(){return this.id;}
    public String getAddress(){return this.address;}
    public String getDescription(){return this.description;}
    public String getContact(){return this.contact;}
    public TypeRestaurant getType(){return type;}
    /*
    * The part of set() methods.
    * */
    public void setId(int id){
        this.id = id;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setType(TypeRestaurant type){
        this.type = type;
    }

    public void setContact(String contact){
        this.contact = contact;
    }
}
