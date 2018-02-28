package com.example.weibinwang.myapplication.Model.ModelInter;

import com.example.weibinwang.myapplication.OnListener.OnLoginListener;
import com.example.weibinwang.myapplication.OnListener.OnRegisterListener;

/**
 * Created by weibinwang on 2018/2/28.
 */

public interface UserModelInter {

    //operation of database for registry
    void register(String username, String email, String password, String firstname, String secondname, OnRegisterListener listener);

    void register(String username,String email, String password, OnRegisterListener listener);

    //operation of database for login
    void login(String identified, String password, OnLoginListener listener, String flag);
}
