package changePassword;

import java.util.List;

import Model.Response;

public interface GantiPassView {
    void showLoading();
    void hideLoading();
    void onSuccess(Response response);
    void onError();
    void onFailure(Throwable t);
}
