package com.example.simadeui.admin.home;

import java.util.List;

import Model.HistoryResponse;

public interface HistoryView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<HistoryResponse> historyResponseList);
    void onError();
    void onFailure(Throwable t);
}
