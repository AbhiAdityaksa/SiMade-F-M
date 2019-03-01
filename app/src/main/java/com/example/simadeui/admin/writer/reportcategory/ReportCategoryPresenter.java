package com.example.simadeui.admin.writer.reportcategory;

import java.util.List;

import Api.ApiService;
import Model.ReportCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportCategoryPresenter {
    private ReportCategoryView view;
    private ApiService service;

    public ReportCategoryPresenter(ReportCategoryView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getReportCategory(){
        view.showLoading();
        service.showReportCategory()
                .enqueue(new Callback<List<ReportCategory>>() {
                    @Override
                    public void onResponse(Call<List<ReportCategory>> call, Response<List<ReportCategory>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        } else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<ReportCategory>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
