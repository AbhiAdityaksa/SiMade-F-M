package com.example.simadeui.admin.writer.carity;

import java.util.List;

import Model.SumCarityResponse;

public interface DataCarityView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<SumCarityResponse> sumCarityResponses);
    void onError();
    void onFailure(Throwable t);
}
