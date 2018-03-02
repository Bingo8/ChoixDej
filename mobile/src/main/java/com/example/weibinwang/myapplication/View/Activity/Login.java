package com.example.weibinwang.myapplication.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Controller.PresenterUser;
import com.example.weibinwang.myapplication.R;
import com.example.weibinwang.myapplication.View.ViewInter.ViewUserOperationInter;

public class Login extends AppCompatActivity implements ViewUserOperationInter{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText mUser;
    private EditText mPassword;
    private Button mBtnLogin;
    private TextView mRegister;
    private ProgressBar mProBar;
    private PresenterUser presenterUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // initial widgets for view
        initView();
        // initial events for widgets
        event();
        // connect controller
        presenterUser = new PresenterUser(this, Login.this);
    }

    private void initView(){
        mUser = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mRegister = (TextView) findViewById(R.id.link_signup);
        mProBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProBar.setIndeterminate(false);
    }

    private void event(){
        /*
        * event for start up login
        * */
        mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenterUser.login();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this,Registry.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }
    @Override
    public String getIdentity() {
        return mUser.getText().toString();
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
    public String getPassword() {
        return mPassword.getText().toString();
    }


    @Override
    public void clearUsername() {
        mUser.setText("");
    }

    @Override
    public void clearUserPass() {
        mPassword.setText("");
    }

    @Override
    public void hideLoading() {
        mProBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mProBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void successHint(User user, String tag) {
        Toast.makeText(Login.this,"User"+user.getUsername()+tag+" success ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failHint(User user, String tag) {
        Toast.makeText(Login.this,"User"+user.getUsername()+tag+" failed, incorrect password or identity", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostExcute() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
