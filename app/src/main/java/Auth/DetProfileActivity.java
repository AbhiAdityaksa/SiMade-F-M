package Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simadeui.R;

import Helper.ConstantURL;
import Helper.PreferenceHelper;

public class DetProfileActivity extends AppCompatActivity {

    private ImageView btn_back, profile;
    private TextView tvName, tvNo, tvStatus, tvKontak, tvEmail, tvKerja, tvTanggal;
    private PreferenceHelper preferenceHelper;
    private Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_profile);

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        init();
        setView();
    }

    private void setView() {
        tvName.setText(preferenceHelper.getName());
        tvNo.setText(preferenceHelper.getNoktp());
        if (preferenceHelper.getWork().equals("1")){
            tvStatus.setText("Bekerja");
        }else {
            tvStatus.setText("Belum Bekerja");
        }
        tvKontak.setText(preferenceHelper.getContact());
        tvEmail.setText(preferenceHelper.getEmail());
        if (preferenceHelper.getWorked().isEmpty()){
            tvKerja.setText("Belum Diatur");
        }else {
            tvKerja.setText(preferenceHelper.getWorked());
        }
        if (preferenceHelper.getBirthday().isEmpty()){
            tvTanggal.setText("Belum Diatur");
        }else {
            tvTanggal.setText(preferenceHelper.getBirthday());
        }
        if (!preferenceHelper.getProfile().isEmpty()){
            Glide.with(this).load(ConstantURL.URL.photoProfile(preferenceHelper.getProfile())).into(profile);
        }

    }

    private void init() {
        preferenceHelper = new PreferenceHelper(this);
        tvName = findViewById(R.id.tv_profile_nama);
        tvNo = findViewById(R.id.tv_profile_ktp);
        tvStatus = findViewById(R.id.tv_profile_work);
        tvKontak = findViewById(R.id.tv_profile_contact);
        tvEmail = findViewById(R.id.tv_profile_email);
        tvKerja = findViewById(R.id.tv_profile_pekerjaan);
        tvTanggal = findViewById(R.id.tv_profile_tanggal_lahir);
        profile = findViewById(R.id.profile_image);
        btn_edit = findViewById(R.id.btn_edit_profile);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        // Don't add finish here.
        //This is necessary because you finished your last activity with finish();
    }
}
