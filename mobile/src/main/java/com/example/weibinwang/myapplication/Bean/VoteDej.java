package com.example.weibinwang.myapplication.Bean;

import com.example.weibinwang.myapplication.Common.Classify.TypeVoteDej;



/**
 * Created by weibinwang on 2018/2/22.
 */

public class VoteDej {
    private int id;
    private User owner;
    private Restaurant purpose;
    private TypeVoteDej type;
    private int times;

    public VoteDej(){
        //do nothing
    }

    /*
    * The part of get() methods.
    * */
    public int getId(){return this.id;}
    public User owner(){return this.owner;}
    public Restaurant getPurpose(){return this.purpose;}
    public int getTimes(){return this.times;}
    public TypeVoteDej getType(){return this.type;}
    /*
    * The part of set() methods.
    * */
    public void setId(int id){
        this.id = id;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }
    public void setPurpose(Restaurant p){
        this.purpose = p;
    }
    public void setType(TypeVoteDej t){
        this.type = t;
    }
    public void setTimes(int times){
        this.times = times;
    }

}
