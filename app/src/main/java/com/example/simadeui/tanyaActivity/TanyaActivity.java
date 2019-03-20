package com.example.simadeui.tanyaActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;

import Api.ApiClient;
import Model.Response;
import utama.UtamaActivity;

public class TanyaActivity extends AppCompatActivity implements TanyaView {
    private static final String TAG = "TanyaActivity";
    TextView tv_title;
    TextView tv_etc;
    ImageView btn_back;
    EditText et_question;
    Button btn_send;
    String title;
    String etc;
    Integer id;
    TanyaPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanya);

        init();
        getExtra();
        setItem();
        sendQuestion();
        back();

    }


    private void back() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init(){
        tv_title = findViewById(R.id.tv_title);
        tv_etc = findViewById(R.id.tv_etc);
        btn_back = findViewById(R.id.btn_back);
        et_question =  findViewById(R.id.et_tanya);
        btn_send = findViewById(R.id.btn_send);


        presenter = new TanyaPresenter(this, ApiClient.getService(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Now Loading...");

    }

    private void sendQuestion(){
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String question = et_question.getText().toString();
                Log.d(TAG, "sendQuestion: question="+question+", id="+id);

                presenter.addQuestion(question,id);
            }
        });
    }

    private void getExtra(){
        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        etc = intent.getStringExtra("Etc");
        id = intent.getIntExtra("Id",0);
    }

    private void setItem(){
        tv_title.setText(title);
        tv_etc.setText(etc);
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
        Intent intent = new Intent(this, UtamaActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Success send report ", Toast.LENGTH_SHORT).show();
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
