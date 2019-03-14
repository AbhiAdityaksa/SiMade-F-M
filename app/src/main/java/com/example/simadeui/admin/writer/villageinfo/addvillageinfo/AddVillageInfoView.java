package com.example.simadeui.admin.writer.villageinfo.addvillageinfo;

import Model.Response;

public interface AddVillageInfoView {
    void showLoading();
    void hideLoading();
    void onSuccess(Response response);
    void onError();
    void onFailure(Throwable t);
}
