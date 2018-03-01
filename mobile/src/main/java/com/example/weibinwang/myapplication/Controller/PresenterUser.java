package com.example.weibinwang.myapplication.Controller;

import android.content.Context;

import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.ModelImp.ModelImp;
import com.example.weibinwang.myapplication.Model.ModelInter.ModelInter;
import com.example.weibinwang.myapplication.View.ViewInter.ViewUserOperationInter;

/**
 * Created by weibinwang on 2018/2/28.
 */

public class PresenterUser {

    private ViewUserOperationInter viewUser;
    private ModelInter modelInter;

    public PresenterUser(ViewUserOperationInter viewUser, Context context){
        this.viewUser = viewUser;
        this.modelInter = new ModelImp(new DBHelper(context));
    }

    public void register(){

    }

}
