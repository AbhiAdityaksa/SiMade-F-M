package com.example.simadeui.admin.writer.villageinfo;

import java.util.List;

import Api.ApiService;
import Model.VillageInfoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageInfoPresenter {

    private VillageInfoView view;
    private ApiService service;

    public VillageInfoPresenter(VillageInfoView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showInfo(){
        view.showLoading();
        service.showInfoDesa()
                .enqueue(new Callback<List<VillageInfoResponse>>() {
                    @Override
                    public void onResponse(Call<List<VillageInfoResponse>> call, Response<List<VillageInfoResponse>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<VillageInfoResponse>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
