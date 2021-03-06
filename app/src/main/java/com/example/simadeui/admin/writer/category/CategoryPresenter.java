package com.example.simadeui.admin.writer.category;

import java.util.List;

import Api.ApiService;
import Model.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class CategoryPresenter {

    private CategoryView view;
    private ApiService service;

    public CategoryPresenter(CategoryView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showCatInfo(){
        view.showLoading();
        service.catInfo()
                .enqueue(new Callback<List<Response>>() {
                    @Override
                    public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
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
