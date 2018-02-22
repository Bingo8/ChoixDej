package com.example.weibinwang.myapplication.Model.DatabaseService;


import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.UserOpe;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class UserService implements UserOpe{

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User queryUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean isExisted(User user) {
        return false;
    }
}
