package com.example.simadeui.admin.writer.carity.listdetail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simadeui.R;

import java.util.List;

import Api.ApiClient;
import Model.DetailSumCarityResponse;
import Model.SumCarityResponse;

public class ListDataCarityActivity extends AppCompatActivity implements ListDataCarityAdapter.OnClickListener, ListDataCarityView {

    public static final String KEY_CARITY = "sumCarityResponse";
    private ImageView btnBack;
    private RecyclerView recyclerView;
    private List<DetailSumCarityResponse> detailSumCarityResponseList;
    private ListDataCarityAdapter adapter;
    private ListDataCarityPresenter presenter;
    private DetailSumCarityResponse detailSumCarityResponse;
    private SumCarityResponse sumCarityResponse;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_carity);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Now Loading...");

        init();
        back();
    }

    private void back() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        btnBack = findViewById(R.id.back_list_data_carity);
        sumCarityResponse = getIntent().getParcelableExtra(KEY_CARITY);
        recyclerView = findViewById(R.id.rv_list_data_sumbangan_admin);

        presenter = new ListDataCarityPresenter(this, ApiClient.getService(this));
        presenter.showDataPenyumbang(sumCarityResponse.getId());
    }

    @Override
    public void onClick(int position) {

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
    public void onSuccess(List<DetailSumCarityResponse> detailSumCarityResponseList) {
        this.detailSumCarityResponseList = detailSumCarityResponseList;
        adapter = new ListDataCarityAdapter(this, detailSumCarityResponseList);
        adapter.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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
