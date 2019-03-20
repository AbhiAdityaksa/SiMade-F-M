package com.example.simadeui.admin.writer.villageinfo.addvillageinfo;

import java.util.List;

import Model.Response;

public interface AddVillageInfoView {
    void showLoading();
    void hideLoading();
    void onSuccess(Response response);
    void onCatInfo(List<Response> responseList);
    void onError();
    void onFailure(Throwable t);
}
