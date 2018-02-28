package com.example.weibinwang.myapplication.Bean;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class User {

    private long id;
    private String firstname;
    private String secondname;
    private String username;
    private String password;
    private String email;

    /*
    * Constructor for user's whole based information.
    * */
    public User(long id, String fistname, String secondname, String username, String password, String mail){
        this.id = id;
        this.firstname = fistname;
        this.secondname = secondname;
        this.username = username;
        this.password = password;
        this.email = mail;
    }
    /*
    * Constructor for user's main based information(id, username, password, mail)
    * */
    public User(long id, String username, String password, String mail){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = mail;
    }
    /*
    * Constructor for implementing Object User for a while.
    * */
    public User(){}

    /*
    * The part of get() methods :
    * */
    public long getId(){return this.id;}
    public String getFirstname(){return this.firstname;}
    public String getSecondname(){return this.secondname;}
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getMail(){return this.email;}

    /*
    * The part of set() methods :
    * */
    public void setId(long id){
        this.id = id;
    }
    public void setFirstname(String text){
        this.firstname = text;
    }
    public void setSecondname(String text){
        this.secondname = text;
    }
    public void setUsername(String text){
        this.username = text;
    }
    public void setPassword(String psd){
        this.password = psd;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
