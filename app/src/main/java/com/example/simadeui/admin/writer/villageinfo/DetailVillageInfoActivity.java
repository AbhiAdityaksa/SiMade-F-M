package com.example.simadeui.admin.writer.villageinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simadeui.R;

import Helper.ConstantURL;
import Helper.DateFormated;
import Model.VillageInfoResponse;

public class DetailVillageInfoActivity extends AppCompatActivity {

    public static final String KEY_INFO = "villageInfoResponse";
    private TextView tvTanggal, tvName, tvEtc;
    private ImageView ivInfo, btnBack;
    private VillageInfoResponse villageInfoResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_village_info);
        
        init();
        setView();
        back();
    }

    private void back() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setView() {
        tvTanggal.setText(DateFormated.setTglHistory(villageInfoResponse.getCreatedAt()));
        tvName.setText(villageInfoResponse.getName());
        tvEtc.setText(villageInfoResponse.getEtc());
        Glide.with(this).load(ConstantURL.URL.photoInfo(villageInfoResponse.getPicture())).into(ivInfo);
    }

    private void init() {
        villageInfoResponse = getIntent().getParcelableExtra(KEY_INFO);

        tvTanggal = findViewById(R.id.tv_info_desa_date_admin_detail);
        tvName = findViewById(R.id.tv_info_desa_name_admin_detail);
        tvEtc = findViewById(R.id.tv_info_desa_etc_admin_detail);

        ivInfo = findViewById(R.id.iv_info_desa_admin_detail);
        btnBack = findViewById(R.id.back_info_desa_admin_detail);
    }
}
