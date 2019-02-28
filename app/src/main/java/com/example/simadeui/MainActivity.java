package com.example.simadeui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.admin.AdminActivity;

import Api.ApiClient;
import Api.ApiService;
import Auth.AuthPresenter;
import Auth.AuthView;
import Helper.ConstantURL;
import Helper.PreferenceHelper;
import Model.User;

public class MainActivity extends AppCompatActivity implements AuthView, View.OnClickListener {

    protected TextView tv_regis1;
    protected EditText et_user1, et_user2;
    protected Button bt_login;
    private PreferenceHelper preferenceHelper;
    private AuthPresenter presenter;
    ApiService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLogin();
        init();
    }

    private void checkLogin(){
        preferenceHelper = new PreferenceHelper(this);
        boolean login = preferenceHelper.getLogin();
        if(login) {
            Intent intent = new Intent(this, UtamaActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void init(){
        et_user1 = (EditText) findViewById(R.id.et_user1);
        et_user2 = (EditText) findViewById(R.id.et_user2);
        bt_login = (Button) findViewById(R.id.bt_login);
        tv_regis1 = (TextView) findViewById(R.id.tv_regis1);

        bt_login.setOnClickListener(this);
        tv_regis1.setOnClickListener(this);
        presenter = new AuthPresenter(this,ApiClient.getService(this));
        preferenceHelper = new PreferenceHelper(this);


    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        preferenceHelper.setUser(user);
        if (user.getPermission().equals(String.valueOf(ConstantURL.Permission.USER))){
            startActivity(new Intent(MainActivity.this,UtamaActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, AdminActivity.class));
        }
        finish();
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(User user) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Failed : "+t, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_regis1:
                Intent intent=new Intent(MainActivity.this,Register2Activity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    private void login(){
        String email = et_user1.getText().toString();
        String password = et_user2.getText().toString();

        if(validate(email,password)){
            presenter.login(email,password);
        }
    }

    private boolean validate(String username,String password){
        if (username.equals("")){
            et_user1.setError("Field username tidak boleh kosong");
            return false;
        }

        if (password.equals("")){
            et_user2.setError("Field password tidak boleh kosong");
            return false;
        }

        return true;
    }
}
