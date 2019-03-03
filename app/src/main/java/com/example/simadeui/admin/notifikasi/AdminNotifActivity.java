package com.example.simadeui.admin.notifikasi;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simadeui.R;

public class AdminNotifActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView btnBack;

    private int[] navIcons = {
        R.drawable.ic_verified_user,
        R.drawable.ic_report,
        R.drawable.ic_question,
        R.drawable.ic_attach_money
    };

    private int[] navLabels = {
        R.string.title_verif_user_admin,
        R.string.title_user_report_admin,
        R.string.title_user_question_admin,
        R.string.title_verif_carity_admin
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notif);

        viewPager = findViewById(R.id.viewpager);
        btnBack = findViewById(R.id.back_notif_admin);
        NotifikasiFragmentPagerAdapter adapter = new NotifikasiFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.admin_notif_tab);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons(tabLayout);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void createTabIcons(TabLayout tabLayout){
        for (int i = 0; i < tabLayout.getTabCount(); i++){
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);

            tab_label.setText(getResources().getString(navLabels[i]));
            tab_icon.setImageResource(navIcons[i]);

            tabLayout.getTabAt(i).setCustomView(tab);
        }
    }
}
