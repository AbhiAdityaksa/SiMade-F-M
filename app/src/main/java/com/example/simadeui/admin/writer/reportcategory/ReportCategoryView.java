package com.example.simadeui.admin.writer.reportcategory;

import java.util.List;

import Model.ReportCategory;

public interface ReportCategoryView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<ReportCategory> reportCategoryList);
    void onError();
    void onFailure(Throwable t);
}
