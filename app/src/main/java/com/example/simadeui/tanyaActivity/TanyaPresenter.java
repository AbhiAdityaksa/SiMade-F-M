package com.example.simadeui.tanyaActivity;

import Api.ApiService;
import Model.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class TanyaPresenter {
    private TanyaView view;
    private ApiService service;

    public TanyaPresenter(TanyaView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void addQuestion(String question, Integer id_info){
//        view.showLoading();
        service.addQuestion(question,id_info)
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
                        view.hideLoading();
                    }
                });
    }
}
