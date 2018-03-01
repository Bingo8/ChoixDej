package com.example.weibinwang.myapplication.OnListener;

import com.example.weibinwang.myapplication.Bean.User;

/**
 * Created by weibinwang on 2018/2/28.
 */

public interface OnLoginListener {
    String TAG = "LOGIN";

    void successLogin(User user);
    void failedLogin(User user);
}
