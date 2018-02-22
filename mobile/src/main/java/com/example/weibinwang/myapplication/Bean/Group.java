package com.example.weibinwang.myapplication.Bean;

import com.example.weibinwang.myapplication.Common.Classify.TypeGroup;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class Group {
    private int id;
    private String name;
    private TypeGroup type;
    private User administrator;

    public Group(){
        //do nothing
    }

    /*
    * The part of get() methods.
    * */
    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public TypeGroup getType(){return this.type;}
    public User getAdministrator(){return this.administrator;}

    /*
    * The part of set() methods
    * */
    public void setId(int id){
        this.id = id;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setType(TypeGroup t){
        this.type = t;
    }
    public void setAdministrator(User admin){
        this.administrator = admin;
    }
}
