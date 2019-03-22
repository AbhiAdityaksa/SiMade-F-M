package Auth;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.simadeui.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Helper.ConstantURL;
import Helper.DateFormated;
import Helper.PreferenceHelper;
import Model.WorkerResponse;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfileActivity extends AppCompatActivity implements EditProfileView {

    private EditText etName, etKtp, etKontak;
    private TextView tvImage, tvTanggal, tvBulan, tvTahun;
    private Button btnSave, btnTanggal;
    private CircleImageView imageProfile;
    private PreferenceHelper preferenceHelper;
    private ImageView btnBack;
    private Spinner s_status, s_pekerjaan;
    private int year_x, month_x, day_x;
    private String tanggalLahir;
    private static final int IMG_REQUEST = 100;
    private Bitmap bitmap;
    MultipartBody.Part body;
    private List<WorkerResponse> workerResponseList;
    private Integer length = 0;
    private ArrayList<String> workerId;
    private ProgressDialog progressDialog;
    private String workerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();
        setView();

        if (preferenceHelper.getBirthday().equals("")){
            year_x = 1998;
            month_x = 5;
            day_x = 11;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Now Loading...");

        s_pekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                workerName = String.valueOf(i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setView() {
        etName.setText(preferenceHelper.getName());
        etKtp.setText(preferenceHelper.getNoktp());
        if (preferenceHelper.getVerified().equals("1")){
            etKtp.setEnabled(false);
        }
        etKontak.setText(preferenceHelper.getContact());
        if (!preferenceHelper.getBirthday().isEmpty()){
            tvTanggal.setText(DateFormated.getTgl(preferenceHelper.getBirthday()));
            tvBulan.setText(DateFormated.getBulan(preferenceHelper.getBirthday()));
            tvTahun.setText(DateFormated.getTahun(preferenceHelper.getBirthday()));
        }
        if (!preferenceHelper.getProfile().isEmpty()){
            Glide.with(this).load(ConstantURL.URL.photoProfile(preferenceHelper.getProfile())).into(imageProfile);
        }
    }

    private void init() {
        preferenceHelper = new PreferenceHelper(this);
        imageProfile = findViewById(R.id.edit_profile_image);
        tvImage = findViewById(R.id.tv_add_image);
        etName = findViewById(R.id.et_profile_nama_edit);
        etKtp = findViewById(R.id.et_ktp_edit);
        s_status = findViewById(R.id.st_work_status_edit);
        etKontak = findViewById(R.id.et_contact_edit);
        s_pekerjaan = findViewById(R.id.s_pekerjaan_edit);
        btnSave = findViewById(R.id.bt_save_profile);
        btnTanggal = findViewById(R.id.btn_tanggal_profile);
        btnBack = findViewById(R.id.back_profile);
        tvTanggal = findViewById(R.id.tv_tanggal_lahir);
        tvBulan = findViewById(R.id.tv_bulan_lahir);
        tvTahun = findViewById(R.id.tv_tahun_lahir);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(tvTanggal, tvBulan, tvTahun);
            }
        });

        tvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkStorePermission()){
                        selectImage();
                    }
                }else {
                    selectImage();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });

    }

    public static final int MY_PERMISSIONS_REQUEST_STORAGE= 1;
    private boolean checkStorePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE);
            }
            return false;
        }else {
            return true;
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!= null && data.getData() != null){
            Uri selectedImage = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imageProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String filePath = getRealPathFromURI_API19(this,selectedImage);
            File fileImg = new File(filePath);

            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileImg);

            body = MultipartBody.Part.createFormData("photo_profile", fileImg.getName(), reqFile);
        }
    }

    private String getRealPathFromURI_API19(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)){

            if (isExternalStorageDocument(uri)){
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)){
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }

            else if (isDownloadsDocument(uri)){
                Cursor cursor = null;

                try {
                    String[] s ={MediaStore.MediaColumns.DISPLAY_NAME};
                    cursor = context.getContentResolver().query(uri,s,null,null,null);
                    String filename = cursor.getString(0);
                    String path = Environment.getExternalStorageDirectory().toString()+"/Download/"+filename;
                    if (!TextUtils.isEmpty(path)){
                        return path;
                    }
                }finally {
                    cursor.close();
                }

                String id = DocumentsContract.getDocumentId(uri);
                if (id.startsWith("raw:")){
                    return id.replaceFirst(Pattern.quote("raw:"), "");
                }
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }

            else if (isMediaDocument(uri)){
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);

            }
        }

        else if ("content".equalsIgnoreCase(uri.getScheme())){
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }

        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private void showDatePickerDialog(final TextView tvTanggal, final TextView tvBulan, final TextView tvTahun) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String strMonth = DateFormated.dateValidation(month+1);
                String strDayOfMonth = DateFormated.dateValidation(dayOfMonth);
                String strDate = year+"-"+DateFormated.dateValidation(month+1)+"-"+DateFormated.dateValidation(dayOfMonth);
                tvTahun.setText(String.valueOf(year));
                tvBulan.setText(DateFormated.getMonthName(strMonth));
                tvTanggal.setText(strDayOfMonth);

                tanggalLahir = strDate;
            }
        }, year_x, month_x, day_x);
        datePickerDialog.show();
    }

    private void saveProfile() {
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
    public void onSuccess(List<WorkerResponse> workerResponses) {
        this.workerResponseList = workerResponses;
        length = workerResponseList.size();

        workerId = new ArrayList<>();
        for (int i = 0; i < length; i++){
            workerId.add(new String(workerResponseList.get(i).getName()));
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, workerId
        );

        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s_pekerjaan.setAdapter(stringArrayAdapter);

        Toast.makeText(this, "Spinner Success", Toast.LENGTH_SHORT).show();
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
