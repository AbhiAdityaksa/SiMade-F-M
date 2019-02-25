package com.example.simadeui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.regex.Pattern;

import Auth.AuthPresenter;
import Auth.AuthView;
import Helper.PreferenceHelper;
import Model.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Register2Activity extends AppCompatActivity implements AuthView, AdapterView.OnItemSelectedListener {

    private static final String TAG = "WorkStatus";
    Button btn_kk_1, btn_ktp_2, btn_regis;
    TextView tv_kk_1, tv_ktp_2;
    EditText et_identity_no, et_name, et_password,et_email, et_contact, et_worked_status;
    Spinner s_workStatus;
    MultipartBody.Part photo_profil, photo_identity;
    String workStatus;
    private PreferenceHelper preferenceHelper;
    private AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        btn_kk_1 = (Button) findViewById(R.id.btn_u_kk);
        tv_kk_1 = (TextView) findViewById(R.id.tv_u_kk);
        btn_ktp_2 = (Button) findViewById(R.id.btn_u_ktp);
        tv_ktp_2 = (TextView) findViewById(R.id.tv_u_ktp);
        btn_regis = (Button) findViewById(R.id.btn_regis);

        et_name = (EditText) findViewById(R.id.et_nama);
        et_password = (EditText) findViewById(R.id.et_password);
        et_identity_no = (EditText) findViewById(R.id.et_ktp);
        et_contact = (EditText) findViewById(R.id.et_contact);
        et_email = (EditText) findViewById(R.id.et_email);
        s_workStatus = (Spinner) findViewById(R.id.s_workstatus);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.workStatus, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s_workStatus.setAdapter(adapter);

        btn_kk_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(Register2Activity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .withFilter(Pattern.compile(".*\\.jpg$"))
                        .start();
            }
        });

        btn_ktp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(Register2Activity.this)
                        .withRequestCode(2000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .withFilter(Pattern.compile(".*\\.jpg$"))
                        .start();
            }
        });

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "registerClick: "+workStatus);

                register(workStatus);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            tv_kk_1.setText(filePath);
            File file = new File(filePath);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            photo_profil = MultipartBody.Part.createFormData("image", file.getName(), reqFile);




        }else if (requestCode == 2000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            tv_ktp_2.setText(filePath);
            File file = new File(filePath);
            RequestBody reqFile1 = RequestBody.create(MediaType.parse("image/*"), file);
            photo_identity = MultipartBody.Part.createFormData("image", file.getName(), reqFile1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Sukses!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Gagal!",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    private void register(String workStatus){
        Log.d(TAG, "register: "+workStatus);
        RequestBody identity_no = RequestBody.create(okhttp3.MultipartBody.FORM, et_identity_no.getText().toString());
        RequestBody name = RequestBody.create(okhttp3.MultipartBody.FORM, et_name.getText().toString());
        RequestBody password = RequestBody.create(okhttp3.MultipartBody.FORM, et_password.getText().toString());
        RequestBody email = RequestBody.create(okhttp3.MultipartBody.FORM, et_email.getText().toString());
        RequestBody contact = RequestBody.create(okhttp3.MultipartBody.FORM, et_contact.getText().toString());
        RequestBody worked_status = RequestBody.create(okhttp3.MultipartBody.FORM, workStatus);

        if(validate(et_identity_no.getText().toString(),et_name.getText().toString(),et_password.getText().toString(),et_email.getText().toString(), et_contact.getText().toString())){
            presenter.register(identity_no,name,password,email,contact,photo_profil,photo_identity,worked_status);
        }

    }

    private boolean validate(String identity_no,String name, String password, String email, String contact){
        if (identity_no.equals("")){
            et_identity_no.setError("Field no identitas tidak boleh kosong");
            return false;
        }

        if (name.equals("")){
            et_name.setError("Field nama tidak boleh kosong");
            return false;
        }

        if (password.equals("")){
            et_password.setError("Field password tidak boleh kosong");
            return false;
        }

        if (email.equals("")){
            et_email.setError("Field email tidak boleh kosong");
            return false;
        }

        if (contact.equals("")){
            et_contact.setError("Field contact tidak boleh kosong");
            return false;
        }

        return true;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        startActivity(new Intent(Register2Activity.this,MainActivity.class));
        finish();
        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Failed : "+t, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            workStatus = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "ITEM : "+ parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        if (parent.getItemAtPosition(position).toString() == "Bekerja"){
            workStatus = "1";
        }else {
            workStatus = "0";
        };
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        workStatus = "0";
    }
}
