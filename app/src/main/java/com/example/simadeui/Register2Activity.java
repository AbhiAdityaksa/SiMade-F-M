package com.example.simadeui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import Api.ApiClient;
import Api.ApiService;
import Auth.AuthPresenter;
import Auth.AuthView;
import Helper.PreferenceHelper;
import Model.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register2Activity extends AppCompatActivity implements AuthView{

    private static final int IMG_REQUEST = 1000;
    private static final String TAG = "WorkStatus";
    Button btn_kk_1, btn_ktp_2, btn_regis;
    TextView tv_kk_1, tv_ktp_2;
    EditText et_identity_no, et_name, et_password,et_email, et_contact, et_worked_status;
    Spinner s_workStatus;
    MultipartBody.Part photo_profil, photo_identity;
    String workStatus ="";
    private PreferenceHelper preferenceHelper;
    private AuthPresenter presenter;
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        presenter = new AuthPresenter(this, ApiClient.getService(this));
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

        if(s_workStatus.getSelectedItemId() == 0){
            workStatus = "1";
        }else {
            workStatus = "0";
        }

        btn_ktp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
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

    private void selectImage(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode==RESULT_OK&&data!=null){
            Uri selectedImage=data.getData();
            String wholeID = DocumentsContract.getDocumentId(selectedImage);

            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];

            String[] column = { MediaStore.Images.Media.DATA };

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = getContentResolver().
                    query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            column, sel, new String[]{ id }, null);

            String filePath = "";

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
            File file = new File(filePath);

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);

            photo_identity = MultipartBody.Part.createFormData("photo_identity", file.getName(), reqFile);
            tv_ktp_2.setText(filePath);
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
        RequestBody contact= RequestBody.create(okhttp3.MultipartBody.FORM, et_contact.getText().toString());
        RequestBody worked_status = RequestBody.create(okhttp3.MultipartBody.FORM, workStatus);

        Log.d(TAG, "photo: "+photo_identity);
        Log.d(TAG, "no_ktp: "+et_identity_no.getText().toString());
        Log.d(TAG, "name: "+et_name.getText().toString());

//        if(validate(et_identity_no.getText().toString(),et_name.getText().toString(),et_password.getText().toString(),et_email.getText().toString(), et_contact.getText().toString())){
            presenter.register(photo_identity,identity_no,name,password,email, contact, worked_status);
//        }

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
    public void onError(User user) {
        Toast.makeText(this, "Error :", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Failed : "+t, Toast.LENGTH_SHORT).show();
    }
}
