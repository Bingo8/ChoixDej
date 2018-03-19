package com.example.weibinwang.myapplication.Bean;

import com.example.weibinwang.myapplication.Common.Classify.TypeGroup;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class Group {
    private long id;
    private String name;
    private TypeGroup type;
    private User administrator;

    public Group(){
        //do nothing
    }

    /*
    * The part of get() methods.
    * */
    public long getId(){return this.id;}
    public String getName(){return this.name;}

    public TypeGroup getType(){return this.type;}
    public User getAdministrator(){return this.administrator;}

    /*
    * The part of set() methods
    * */
    public void setId(long id){
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
