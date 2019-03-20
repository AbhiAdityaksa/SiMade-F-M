package utama;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simadeui.R;

import Adapter.ViewPagerAdapter;
import notifActivity.NotifActivity;

public class UtamaActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView btn_notif;

    private int[] navIcons = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_lapor,
            R.drawable.ic_profile
    };

    private int[] navLabel = {
            R.string.title_home,
            R.string.title_dashboard,
            R.string.title_notifications
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        viewPager = findViewById(R.id.viewPager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcon(tabLayout);

        btn_notif = findViewById(R.id.btn_notif);
        btn_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtamaActivity.this, NotifActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createTabIcon(TabLayout tabLayout) {
        for (int i = 0; i < tabLayout.getTabCount(); i++){
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab_villager, null);

            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label_villager);
            ImageView tab_img = (ImageView) tab.findViewById(R.id.nav_icon_villager);

            tab_label.setText(getResources().getString(navLabel[i]));
            tab_img.setImageResource(navIcons[i]);
            tab_img.setColorFilter(getResources().getColor(R.color.colorutama));

            tabLayout.getTabAt(i).setCustomView(tab);
//            tabLayout.getTabAt(i).getIcon().setColorFilter(Color.parseColor("#000"), PorterDuff.Mode.SRC_IN);
        }
    }

}
