package com.example.weibinwang.myapplication.Controller;

import android.content.Context;

import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Common.Validator.EmailValidator;
import com.example.weibinwang.myapplication.Model.ModelImp.ModelImp;
import com.example.weibinwang.myapplication.Model.ModelInter.ModelInter;
import com.example.weibinwang.myapplication.OnListener.OnLoginListener;
import com.example.weibinwang.myapplication.OnListener.OnRegisterListener;
import com.example.weibinwang.myapplication.View.ViewInter.ViewUserOperationInter;

/**
 * Created by weibinwang on 2018/2/28.
 */

public class PresenterUser {

    private ViewUserOperationInter viewUser;
    private ModelInter modelInter;

    public PresenterUser(ViewUserOperationInter viewUser, Context context){
        this.viewUser = viewUser;
        this.modelInter = new ModelImp(context);
    }

    public void register(){
        viewUser.showLoading();
        modelInter.register(viewUser.getUsername(), viewUser.getEmail(), viewUser.getPassword(), new OnRegisterListener() {
            @Override
            public void successRegister(final User user) {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewUser.hideLoading();
                        viewUser.successHint(user,TAG);
                        viewUser.onPostExcute();
                    }
                },3000);
            }

            @Override
            public void failedRegister(final User user) {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewUser.hideLoading();
                        viewUser.failHint(user, TAG);
                    }
                },3000);
            }
        });

    }

    public void login() {
        viewUser.showLoading();
        if(EmailValidator.validateEmail(viewUser.getIdentity())){
            modelInter.login(viewUser.getIdentity(), viewUser.getPassword(), new OnLoginListener() {
                @Override
                public void successLogin(final User user) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    viewUser.hideLoading();
                                    viewUser.successHint(user, TAG);
                                }
                            },3000
                    );


                }

                @Override
                public void failedLogin(final User user) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    viewUser.hideLoading();
                                    viewUser.failHint(user, TAG);
                                }
                            },3000
                    );
                }
            }, "EMAIL");
        }
        else{
            modelInter.login(viewUser.getIdentity(), viewUser.getPassword(), new OnLoginListener() {
                @Override
                public void successLogin(final User user) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    viewUser.hideLoading();
                                    viewUser.successHint(user, TAG);
                                }
                            },3000
                    );
                }

                @Override
                public void failedLogin(final User user) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    viewUser.hideLoading();
                                    viewUser.failHint(user, TAG);
                                }
                            },3000
                    );
                }
            }, " ");
        }
    }
}
