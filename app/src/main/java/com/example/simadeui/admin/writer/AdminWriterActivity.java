package com.example.simadeui.admin.writer;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simadeui.R;

public class AdminWriterActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int[] navIcons = {
        R.drawable.ic_info_outline,
        R.drawable.ic_view_list,
        R.drawable.ic_list
    };

    private int[] navLabels = {
        R.string.title_info_desa_admin,
        R.string.title_report_category_admin,
        R.string.title_category_admin
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_writer);

        viewPager = findViewById(R.id.view_pager_admin_writer);
        AdminWriterFragmentPagerAdapter adapter = new AdminWriterFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.admin_writer_tab);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons(tabLayout);
    }

    private void createTabIcons(TabLayout tabLayout) {
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
