package com.example.weibinwang.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Model.ModelImp.ModelImp;
import com.example.weibinwang.myapplication.OnListener.OnLoginListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by weibinwang on 2018/3/1.
 */
@RunWith(AndroidJUnit4.class)
public class ModelImpInstrumentedTest {
    private ModelImp modelImp;
    @Before
    public void setUp(){
        Context context = InstrumentationRegistry.getTargetContext();
        modelImp = new ModelImp(context);
    }
    @Test
    public void validateLogin() throws Exception{

        modelImp.login("bign", "djskjk", new OnLoginListener() {
            @Override
            public void successLogin(User user) {
                Log.println(1,"LOGIN","OK");
            }

            @Override
            public void failedLogin(User user) {
                Log.println(2,"LOGIN","NON");
            }
        }," ");
    }
}
