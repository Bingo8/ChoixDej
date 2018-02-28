package com.example.weibinwang.myapplication.Bean;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class VoteNote {
    private long id;
    private User owner;
    private Restaurant purpose;
    private int note;

    public VoteNote(){
        //do nothing
    }

    /*
    * The part of get() methods.
    * */

    public long getId(){return this.id;}
    public User owner(){return this.owner;}
    public Restaurant getPurpose(){return this.purpose;}
    public int getNote(){return this.note;}

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

    public void setNote(int note){
        this.note = note;
    }
}
