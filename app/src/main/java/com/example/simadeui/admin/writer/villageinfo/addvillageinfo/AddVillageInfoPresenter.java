package com.example.simadeui.admin.writer.villageinfo.addvillageinfo;

import java.util.List;

import Api.ApiService;
import Model.Response;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AddVillageInfoPresenter {

    private AddVillageInfoView view;
    private ApiService service;

    public AddVillageInfoPresenter(AddVillageInfoView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void addInfo(MultipartBody.Part picture, RequestBody name, RequestBody etc, RequestBody category_id){
        view.showLoading();
        service.addInfo(picture, name, etc, category_id)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        view.onFailure(t);
                    }
                });
    }

    public void addInfoSumbangan(MultipartBody.Part picture, RequestBody name, RequestBody etc, RequestBody category_id, final RequestBody valid){
        view.showLoading();
        service.addInfoSumbangan(picture, name, etc, category_id, valid)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        view.onFailure(t);
                    }
                });
    }

    public void showCatInfo(){
        view.showLoading();
        service.catInfo()
                .enqueue(new Callback<List<Response>>() {
                    @Override
                    public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                        if (response.isSuccessful()){
                            view.onCatInfo(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<Response>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
