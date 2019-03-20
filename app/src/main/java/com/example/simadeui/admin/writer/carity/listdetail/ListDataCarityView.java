package com.example.simadeui.admin.writer.carity.listdetail;

import java.util.List;

import Model.DetailSumCarityResponse;

public interface ListDataCarityView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<DetailSumCarityResponse> detailSumCarityResponseList);
    void onError();
    void onFailure(Throwable t);
}
