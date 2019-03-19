package com.example.simadeui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TanyaActivity extends AppCompatActivity {
    private TextView tv_title;
    private TextView tv_etc;
    private ImageView btn_back;
    private String title;
    private String etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanya);

        init();
        getExtra();
        setItem();
        back();
    }

    private void back() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init(){
        tv_title = findViewById(R.id.tv_title);
        tv_etc = findViewById(R.id.tv_etc);
        btn_back = findViewById(R.id.btn_back);
    }

    private void getExtra(){
        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        etc = intent.getStringExtra("Etc");

    }

    private void setItem(){
        tv_title.setText(title);
        tv_etc.setText(etc);
    }
}
