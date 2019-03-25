package notifActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simadeui.HomeActivityFrag;
import com.example.simadeui.R;

import Auth.DetProfileActivity;
import Auth.MainActivity;
import userReport.LaporanActivityFrag;
import utama.UtamaActivity;

public class NotifActivity extends AppCompatActivity {

    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        btn_back = findViewById(R.id.btn_back);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifActivity.this,UtamaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        Intent a = new Intent(MainActivity.this,UtamaActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_dashboard:
                        Intent b = new Intent(MainActivity.this, LaporanActivityFrag.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_notifications:
                        break;
                }
                return false;
            }
        });

    }

}
