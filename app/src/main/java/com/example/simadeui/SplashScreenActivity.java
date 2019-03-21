package com.example.simadeui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.simadeui.admin.AdminActivity;

import Auth.MainActivity;
import Helper.ConstantURL;
import Helper.PreferenceHelper;
import utama.UtamaActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferenceHelper = new PreferenceHelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (preferenceHelper.getLogin()){
                    if (preferenceHelper.getPermission().equals(String.valueOf(ConstantURL.Permission.USER))){
                        intent = new Intent(SplashScreenActivity.this, UtamaActivity.class);
                    }else {
                        intent = new Intent(SplashScreenActivity.this, AdminActivity.class);
                    }
                }else {
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
