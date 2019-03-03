package com.example.simadeui.admin.writer.reportcategory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.reportcategory.addreportcategory.AddReportCategoryActivity;

import java.util.List;

import Api.ApiClient;
import Model.ReportCategory;

public class ReportCategoryFragment extends Fragment implements ReportCategoryAdapter.OnClickListener, ReportCategoryView {

    private List<ReportCategory> reportCategoryList;
    private RecyclerView rvReportCategory;
    private ReportCategoryAdapter adapter;
    private ReportCategoryPresenter presenter;
    private ReportCategory reportCategory;
    private ProgressDialog progressDialog;
    private TextView tvKosong;
    private FloatingActionButton floatingActionButton;

    public ReportCategoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_category_admin, container, false);
        rvReportCategory = view.findViewById(R.id.rv_report_category_admin);
        tvKosong = view.findViewById(R.id.tv_report_category_admin_kosong);
        floatingActionButton = view.findViewById(R.id.add_report_category);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");

        presenter = new ReportCategoryPresenter(this, ApiClient.getService(getContext()));
        presenter.getReportCategory();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddReportCategoryActivity.class);
                startActivity(intent);
            }
        });
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
    public void onSuccess(List<ReportCategory> reportCategoryList) {
        if (reportCategoryList.isEmpty()){
            tvKosong.setVisibility(View.VISIBLE);
            rvReportCategory.setVisibility(View.GONE);
        } else {
            tvKosong.setVisibility(View.GONE);
            rvReportCategory.setVisibility(View.VISIBLE);
            this.reportCategoryList = reportCategoryList;
            adapter = new ReportCategoryAdapter(getContext(), reportCategoryList);
            adapter.setOnClickListener(this);
            rvReportCategory.setLayoutManager(new LinearLayoutManager(getContext()));
            rvReportCategory.setAdapter(adapter);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}
