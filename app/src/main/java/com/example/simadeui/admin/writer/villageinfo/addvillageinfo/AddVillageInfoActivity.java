package com.example.simadeui.admin.writer.villageinfo.addvillageinfo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.AdminWriterActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Api.ApiClient;
import Helper.DateFormated;
import Model.Response;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddVillageInfoActivity extends AppCompatActivity implements AddVillageInfoView {

    private ImageView btnBack;
    private static final int IMG_REQUEST = 1000;
    private static final String TAG = "CategoryId";
    private Button btnAdd, btnImage, btnValid;
    private EditText etName, etEtc;
    private Spinner s_categoryId;
    private TextView tvTanggal, tvBulan, tvTahun, tvImage, tvCaptionValid, tvResult;
    private int year_x, month_x, day_x;
    private AddVillageInfoPresenter presenter;
    private String categoryId = "", dateValid;
    private LinearLayout layoutValid;
    private MultipartBody.Part picture;
    private List<Response> responseList;
    private Integer length = 0;
    private ArrayList<String> catInfo;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_village_info);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Now Loading...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        btnBack = findViewById(R.id.back_add_village_info);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        init();

    }

    private void init() {
        btnAdd = findViewById(R.id.btn_save_info);
        btnImage = findViewById(R.id.btn_u_foto_info);
        btnValid = findViewById(R.id.btn_tgl_valid);
        etName = findViewById(R.id.et_info_name);
        etEtc = findViewById(R.id.et_info_desc);
        s_categoryId = findViewById(R.id.s_category_info);
        tvTanggal = findViewById(R.id.tv_tgl_valid);
        tvBulan = findViewById(R.id.tv_bulan_valid);
        tvTahun = findViewById(R.id.tv_tahun_valid);
        tvImage = findViewById(R.id.tv_u_foto_info);
        tvCaptionValid = findViewById(R.id.tv_batas_akhir_sumbangan);
        layoutValid = findViewById(R.id.layout_tanggal_valid);
        tvResult = findViewById(R.id.tv_s_result);

        presenter = new AddVillageInfoPresenter(this, ApiClient.getService(this));
        presenter.showCatInfo();
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.infoCat, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s_categoryId.setAdapter(adapter);*/

        year_x=2018;
        day_x=11;
        month_x=9;

        /*String[] catInfo = new String[]{
                "Pilih Kategori",
                "Sumbangan",
                "Informasi",
                "Kegiatan",
                "Pengumuman",
                "Lainnya"
        };*/

        s_categoryId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryId = String.valueOf(i+1);

                if (categoryId.equals("1")){
                    tvCaptionValid.setVisibility(View.VISIBLE);
                    layoutValid.setVisibility(View.VISIBLE);
                } else {
                    tvCaptionValid.setVisibility(View.GONE);
                    layoutValid.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(tvTanggal, tvBulan, tvTahun);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan(categoryId);
            }
        });
    }

    private void simpan(String categoryId) {
        RequestBody name = RequestBody.create(okhttp3.MultipartBody.FORM, etName.getText().toString());
        RequestBody etc = RequestBody.create(okhttp3.MultipartBody.FORM, etEtc.getText().toString());
        RequestBody category_id = RequestBody.create(okhttp3.MultipartBody.FORM, categoryId);

        if (categoryId.equals("1")){
            RequestBody valid = RequestBody.create(okhttp3.MultipartBody.FORM, dateValid);
            presenter.addInfoSumbangan(picture, name, etc, category_id, valid);
        } else {
            presenter.addInfo(picture, name, etc, category_id);
        }
    }

    private void showDatePickerDialog(final TextView tvTanggal, final TextView tvBulan, final TextView tvTahun) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String strMonth = DateFormated.dateValidation(month+1);
                String strDayOfMonth = DateFormated.dateValidation(dayOfMonth);
                String strDate = year+"-"+DateFormated.dateValidation(month+1)+"-"+DateFormated.dateValidation(dayOfMonth);
                tvTahun.setText(String.valueOf(year));
                tvBulan.setText(DateFormated.getMonthName(strMonth));
                tvTanggal.setText(strDayOfMonth);

                dateValid = strDate;
            }
        }, year_x, month_x, day_x);
        datePickerDialog.show();
    }

    private void selectImage() {
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
            picture = MultipartBody.Part.createFormData("picture", file.getName(), reqFile);
            tvImage.setText(filePath);
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

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void onSuccess(Response response) {
        Intent intent = new Intent(this, AdminWriterActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCatInfo(List<Response> responseList) {
        this.responseList = responseList;
        length = responseList.size();

        catInfo = new ArrayList<>();
        for (int i = 0; i < length; i++){
            catInfo.add(new String(responseList.get(i).getName()));
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, catInfo
        );

        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s_categoryId.setAdapter(stringArrayAdapter);

//        Toast.makeText(this, "Success Set Spinner", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
