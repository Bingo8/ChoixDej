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
import com.example.weibinwang.myapplication.Common.Validator.EmailValidator;
import com.example.weibinwang.myapplication.Controller.PresenterUser;
import com.example.weibinwang.myapplication.R;
import com.example.weibinwang.myapplication.View.ViewInter.ViewUserOperationInter;

public class Registry extends AppCompatActivity implements ViewUserOperationInter{
    private static final String TAG = "registerActivity";

    private EditText mUserName;
    private EditText mEmail;
    private EditText mUserPwd;
    private EditText mUserPwd2;

    private Button mBtnRegister;
    private ProgressBar mProBar;
    private TextView mLogin;
    private PresenterUser presenterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        initView();
        event();
        presenterUser = new PresenterUser(this,Registry.this);
    }


    private void initView(){
        mUserName = (EditText)findViewById(R.id.input_name);
        mEmail = (EditText)findViewById(R.id.input_email);
        mUserPwd = (EditText)findViewById(R.id.input_password);
        mUserPwd2 = (EditText)findViewById(R.id.input_password2);
        mBtnRegister = (Button)findViewById(R.id.btn_signup);
        mLogin = (TextView)findViewById(R.id.link_login);
        mProBar = (ProgressBar)findViewById(R.id.progress_bar);
    }

    private void event(){
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validatePassword()){
                    Toast.makeText(getBaseContext(),"different password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!EmailValidator.validateEmail(getEmail())){
                    Toast.makeText(getBaseContext()," error email",Toast.LENGTH_SHORT).show();
                    return;
                }
                presenterUser.register();
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Registry.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validatePassword(){
        return this.getPassword().equals(this.getPassword2()) ;
    }

    @Override
    public String getIdentity() {
        return null;
    }
    @Override
    public String getUsername(){
        return mUserName.getText().toString();
    }
    @Override
    public String getEmail(){
        return mEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return mUserPwd.getText().toString();
    }

    public String getPassword2(){
        return mUserPwd2.getText().toString();
    }

    @Override
    public void clearUsername() {

    }


    @Override
    public void clearUserPass() {

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
        Toast.makeText(Registry.this,"Welcome "+getUsername()+tag,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failHint(User user, String tag) {
        Toast.makeText(Registry.this, "Username is existed !"+tag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExcute() {
        Intent intent = new Intent(Registry.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
