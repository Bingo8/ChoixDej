package com.example.weibinwang.myapplication.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weibinwang.myapplication.Controller.PresenterUser;
import com.example.weibinwang.myapplication.R;

public class Registry extends AppCompatActivity {

    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnLogin;
    private Button mBtnClear;
    private ProgressBar mProBar;
    private TextView mRegister;
    private PresenterUser presenterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
    }
}
