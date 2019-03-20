package com.example.simadeui.admin.writer.category;

import java.util.List;

import Model.Response;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<Response> responseList);
    void onError();
    void onFailure(Throwable t);
}
