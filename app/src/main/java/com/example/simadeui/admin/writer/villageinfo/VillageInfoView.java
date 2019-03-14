package com.example.simadeui.admin.writer.villageinfo;

import java.util.List;

import Model.VillageInfoResponse;

public interface VillageInfoView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<VillageInfoResponse> villageInfoResponseList);
    void onError();
    void onFailure(Throwable t);
}
