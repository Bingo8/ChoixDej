package com.example.weibinwang.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.example.weibinwang.myapplication.Bean.Restaurant;
import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.UserOpe;
import com.example.weibinwang.myapplication.Model.DatabaseService.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by weibinwang on 2018/2/25.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseUnitTest {

    private DBHelper dbHelper;
    private UserOpe us;
    @Before
    public void datatest() throws Throwable{
        Context context = InstrumentationRegistry.getContext();
        dbHelper = new DBHelper(context);
        dbHelper.getReadableDatabase();
        us = new UserService(context);
    }

    @Test
    public void creatObject() throws Exception{
        User user = new User();
        Restaurant restaurant = new Restaurant();

        user.setUsername("WANG");
        user.setEmail("weibin.wang.fr@hotmail.com");
        user.setPassword("psd");
        us.addUser(user);
        Log.i("Test","OK");
    }

    @Test
    public void validaterUser() throws Exception{

        String username = "WANG";
        String email = "weibin.wang.fr@hotmail.com";
        User user = us.queryUserByEmail(email);
        if(user.getUsername().equals(username)){
            Log.i("TAG","Success");
        }else{
            Log.i("TAG","Failed");
        }
    }

    @After
    public void finish(){
        dbHelper.close();
    }

}
