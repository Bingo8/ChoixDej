package com.example.weibinwang.myapplication.Common.Classify;

/**
 * Created by weibinwang on 2018/2/22.
 */

public enum TypeVoteDej {

    YES("Yes"),
    NO("No");

    private String name = "";

    TypeVoteDej(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
