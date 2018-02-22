package com.example.weibinwang.myapplication.Common.Classify;

/**
 * Created by weibinwang on 2018/2/22.
 */

public enum TypeRestaurant {
    CHINESE("Chinese"),
    FRENCH("French"),
    ITALIEN("Italien"),
    JAPANESE("Japanese");

    private String name = "";

    TypeRestaurant(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
