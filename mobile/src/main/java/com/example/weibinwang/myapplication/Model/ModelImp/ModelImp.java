package com.example.weibinwang.myapplication.Model.ModelImp;

import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.RestaurantOpe;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.UserOpe;
import com.example.weibinwang.myapplication.Model.DatabaseService.RestaurantService;
import com.example.weibinwang.myapplication.Model.DatabaseService.UserService;
import com.example.weibinwang.myapplication.Model.ModelInter.ModelInter;
import com.example.weibinwang.myapplication.OnListener.OnLoginListener;
import com.example.weibinwang.myapplication.OnListener.OnRegisterListener;

/**
 * Created by weibinwang on 2018/2/28.
 */

public class ModelImp implements ModelInter {

    private UserOpe userOperation;
    private RestaurantOpe restaurantOperation;


    public ModelImp(DBHelper dbHelper){
        userOperation = new UserService(dbHelper);
        this.restaurantOperation = new RestaurantService(dbHelper);

    }

    @Override
    public void register(String username, String email, String password, String firstname, String secondname, OnRegisterListener listener) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setSecondname(secondname);

        if(userOperation.isExistedUsername(user.getUsername())){
            listener.failedRegister(user);
        }else{
            userOperation.addUser(user);
            listener.successRegister(user);
        }
    }

    @Override
    public void register(String username, String email, String password, OnRegisterListener listener) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        if(userOperation.isExistedUsername(user.getUsername())){
            listener.failedRegister(user);
        }else{
            userOperation.addUser(user);
            listener.successRegister(user);
        }
    }

    @Override
    public void login(String identified, String password, OnLoginListener listener, String flag) {
        User user = new User();
        if(flag.toUpperCase().equals("EMAIL")){
            user.setEmail(identified);
        }else{
            user.setUsername(identified);
        }
        //判断是否登录成功
        if(userOperation.isLoginSuccess(user)){
            listener.successLogin(user);
        }else{
            listener.failedLogin(user);
        }
    }



}
