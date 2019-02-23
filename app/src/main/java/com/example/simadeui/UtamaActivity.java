package com.example.simadeui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class UtamaActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectfrag = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectfrag = new HomeActivityFrag();
                    break;
                case R.id.navigation_dashboard:
                    selectfrag = new LaporanActivityFrag();
                    break;
                case R.id.navigation_notifications:
                    selectfrag = new ProfileActivityFrag();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,selectfrag).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
