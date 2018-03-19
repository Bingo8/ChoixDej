package com.example.weibinwang.myapplication.Bean;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class VoteDej {

    private long id;
    private User owner;
    private Restaurant purpose;

    public VoteDej(){
        //do nothing
    }

    /*
    * The part of get() methods.
    * */
    public long getId(){return this.id;}
    public User getOwner(){return this.owner;}
    public Restaurant getPurpose(){return this.purpose;}

    /*
    * The part of set() methods.
    * */
    public void setId(long id){
        this.id = id;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }
    public void setPurpose(Restaurant p){
        this.purpose = p;
    }


}
