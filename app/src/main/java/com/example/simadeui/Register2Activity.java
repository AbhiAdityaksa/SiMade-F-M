package com.example.simadeui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class Register2Activity extends AppCompatActivity {

    Button btn_kk_1;
    Button btn_ktp_2;
    TextView tv_kk_1;
    TextView tv_ktp_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        btn_kk_1 = (Button) findViewById(R.id.btn_u_kk);
        tv_kk_1 = (TextView) findViewById(R.id.tv_u_kk);

        btn_kk_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(Register2Activity.this)
                        .withRequestCode(1000)
                        .withFilter(Pattern.compile(".*\\.jpg$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(true) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
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
        }
    }
}
