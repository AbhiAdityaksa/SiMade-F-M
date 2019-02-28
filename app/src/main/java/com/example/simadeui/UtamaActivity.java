package com.example.simadeui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import Adapter.ViewPagerAdapter;

public class UtamaActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            Fragment selectfrag = new HomeActivityFrag();
//
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    selectfrag = new HomeActivityFrag();
//                    break;
//                case R.id.navigation_dashboard:
//                    selectfrag = new LaporanActivityFrag();
//                    break;
//                case R.id.navigation_notifications:
//                    selectfrag = new ProfileActivityFrag();
//                    break;
//            }
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectfrag).commit();
//
//            return true;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        tabLayout = findViewById(R.id.tabLayout_id);
//        viewPager = findViewById(R.id.viewPager_id);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //add fragment here
        viewPagerAdapter.AddFragment(new HomeActivityFrag());
        viewPagerAdapter.AddFragment(new LaporanActivityFrag());
        viewPagerAdapter.AddFragment(new ProfileActivityFrag());

        viewPagerAdapter.AddTitle("Home");
        viewPagerAdapter.AddTitle("Lapor");
        viewPagerAdapter.AddTitle("Profile");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_log);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_lapor);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_profile);


    }

}
