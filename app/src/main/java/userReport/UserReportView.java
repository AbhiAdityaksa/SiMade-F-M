package userReport;

import Model.Response;

public interface UserReportView {
    void showLoading();
    void hideLoading();
    void onSuccess(Response response);
    void onError();
    void onFailure(Throwable t);
}
