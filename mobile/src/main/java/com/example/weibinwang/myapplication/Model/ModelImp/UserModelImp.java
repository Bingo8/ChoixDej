package com.example.weibinwang.myapplication.Model.ModelImp;

import com.example.weibinwang.myapplication.Model.ModelInter.UserModelInter;
import com.example.weibinwang.myapplication.OnListener.OnLoginListener;
import com.example.weibinwang.myapplication.OnListener.OnRegisterListener;

/**
 * Created by weibinwang on 2018/2/28.
 */

public class UserModelImp implements UserModelInter {
    @Override
    public void register(String username, String email, String password, String firstname, String secondname, OnRegisterListener listener) {

    }

    @Override
    public void register(String username, String email, String password, OnRegisterListener listener) {

    }

    @Override
    public void login(String identified, String password, OnLoginListener listener) {

    }
}
