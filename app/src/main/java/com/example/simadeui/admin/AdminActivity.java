package com.example.simadeui.admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.simadeui.R;
import com.example.simadeui.admin.graph.GraphAdminFragment;
import com.example.simadeui.admin.home.HomeAdminFragment;
import com.example.simadeui.admin.notifikasi.AdminNotifActivity;
import com.example.simadeui.admin.profile.AdminProfileFragment;

public class AdminActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageView imNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bottomNavigationView = findViewById(R.id.navigationadmin);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutadmin,
                new HomeAdminFragment()).commit();

        imNotif = findViewById(R.id.admin_notif_button);
        imNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminNotifActivity.class);
                startActivity(intent);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.navigation_admin_home:
                    selectedFragment = new HomeAdminFragment();
                    break;
                case R.id.navigation_admin_grafik:
                    selectedFragment = new GraphAdminFragment();
                    break;
                case R.id.navigation_admin_profile:
                    selectedFragment = new AdminProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutadmin,
                    selectedFragment).commit();

            return true;
        }
    };
}
