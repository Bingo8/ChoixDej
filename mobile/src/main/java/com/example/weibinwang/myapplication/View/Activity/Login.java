package com.example.weibinwang.myapplication.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.R;
import com.example.weibinwang.myapplication.View.Activity.ViewInter.ViewUserOperationInter;

public class Login extends AppCompatActivity implements ViewUserOperationInter{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void clearUsername() {

    }

    @Override
    public void clearUserPass() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void successHint(User user, String tag) {

    }

    @Override
    public void failHint(User user, String tag) {

    }
}
