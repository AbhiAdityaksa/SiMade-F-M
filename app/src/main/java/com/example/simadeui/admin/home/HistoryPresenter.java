package com.example.simadeui.admin.home;

import java.util.List;

import Api.ApiService;
import Model.HistoryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter {
    private HistoryView view;
    private ApiService service;

    public HistoryPresenter(HistoryView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showHistory(){
        view.showLoading();
        service.showHistory()
                .enqueue(new Callback<List<HistoryResponse>>() {
                    @Override
                    public void onResponse(Call<List<HistoryResponse>> call, Response<List<HistoryResponse>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<HistoryResponse>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
