package com.example.simadeui.admin.writer.carity;

import java.util.List;

import Api.ApiService;
import Model.SumCarityResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCarityPresenter {

    private DataCarityView view;
    private ApiService service;

    public DataCarityPresenter(DataCarityView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showSumCarity(){
        view.showLoading();
        service.showSumCarity()
                .enqueue(new Callback<List<SumCarityResponse>>() {
                    @Override
                    public void onResponse(Call<List<SumCarityResponse>> call, Response<List<SumCarityResponse>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<SumCarityResponse>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
