package com.example.weibinwang.myapplication.OnListener;

import com.example.weibinwang.myapplication.Bean.User;

/**
 * Created by weibinwang on 2018/2/28.
 */

public interface OnRegisterListener {
    String TAG = "REGISTER";

    void successRegister(User user);

    void failedRegister(User user);
}
