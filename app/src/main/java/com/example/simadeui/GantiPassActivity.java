package com.example.simadeui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GantiPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_pass);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GantiPassActivity.this,UtamaActivity.class);
        intent.putExtra("FRAG", "Profile");
        startActivity(intent);
        finish();
        // Don't add finish here.
        //This is necessary because you finished your last activity with finish();
    }
}
