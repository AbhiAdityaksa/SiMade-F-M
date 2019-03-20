package com.example.simadeui.admin.writer.carity.listdetail;

import java.util.List;

import Api.ApiService;
import Model.DetailSumCarityResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDataCarityPresenter {

    private ListDataCarityView view;
    private ApiService service;

    public ListDataCarityPresenter(ListDataCarityView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showDataPenyumbang(int id){
        view.showLoading();
        service.showListPenyumbang(id)
                .enqueue(new Callback<List<DetailSumCarityResponse>>() {
                    @Override
                    public void onResponse(Call<List<DetailSumCarityResponse>> call, Response<List<DetailSumCarityResponse>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<DetailSumCarityResponse>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
