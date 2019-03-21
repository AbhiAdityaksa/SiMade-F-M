package com.example.simadeui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simadeui.tanyaActivity.TanyaActivity;

import Helper.ConstantURL;
import Helper.DateFormated;
import Model.VillageInfoResponse;

public class DetPostActivity extends AppCompatActivity {

    public static final String KEY_INFO = "villageInfoResponse";
    private TextView tvTanggal, tvName, tvEtc;
    private ImageView imageView, imageBack;
    private Button btnTanya;
    private VillageInfoResponse villageInfoResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_post);

        init();
        setView();
        tanya();
        back();
    }

    private void back() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void tanya(){
        btnTanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetPostActivity.this, TanyaActivity.class);
                intent.putExtra("Id",villageInfoResponse.getId());
                intent.putExtra("Title",villageInfoResponse.getName());
                intent.putExtra("Etc",villageInfoResponse.getEtc());
                intent.putExtra("Date",villageInfoResponse.getCreatedAt());
                startActivity(intent);
                finish();
            }
        });
    }

    private void setView() {
        tvTanggal.setText(DateFormated.setTglHistory(villageInfoResponse.getCreatedAt()));
        tvName.setText(villageInfoResponse.getName());
        tvEtc.setText(villageInfoResponse.getEtc());
        Glide.with(this).load(ConstantURL.URL.photoInfo(villageInfoResponse.getPicture())).into(imageView);
    }

    private void init() {
        villageInfoResponse = getIntent().getParcelableExtra(KEY_INFO);

        tvTanggal = findViewById(R.id.tv_date_news_detail);
        tvName = findViewById(R.id.tv_name_news_detail);
        tvEtc = findViewById(R.id.tv_etc_news_detail);
        imageView = findViewById(R.id.iv_image_news_detail);
        imageBack = findViewById(R.id.det_post_back);
        btnTanya = findViewById(R.id.btn_question);

    }
}
