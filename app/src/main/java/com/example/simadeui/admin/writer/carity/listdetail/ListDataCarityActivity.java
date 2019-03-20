package com.example.simadeui.admin.writer.carity.listdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.simadeui.R;

public class ListDataCarityActivity extends AppCompatActivity {

    public static final String KEY_CARITY = "sumCarityResponse";
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_carity);

        init();
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

    private void init() {
        btnBack = findViewById(R.id.back_list_data_carity);
    }
}
