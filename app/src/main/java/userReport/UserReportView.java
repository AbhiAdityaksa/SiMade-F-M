package userReport;

import java.util.List;

import Model.Response;

public interface UserReportView {
    void showLoading();
    void hideLoading();
    void onSuccess(Response response);
    void onSuccess(List <Response> response);
    void onError();
    void onFailure(Throwable t);
}


