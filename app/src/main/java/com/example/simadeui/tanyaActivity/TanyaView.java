package com.example.simadeui.tanyaActivity;

import Model.Response;

public interface TanyaView {
    void showLoading();
    void hideLoading();
    void onSuccess(Response response);
    void onError();
    void onFailure(Throwable t);
}
