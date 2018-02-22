package com.example.weibinwang.myapplication.Common.Classify;

/**
 * Created by weibinwang on 2018/2/22.
 */

public enum TypeGroup {
    OPEN("Open"),
    PRIVATE("Private");

    private String name = "";

    TypeGroup(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
