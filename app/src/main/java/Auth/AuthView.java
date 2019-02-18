package Auth;

import Model.UserLogin;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void onSuccess(UserLogin userLogin);
    void onError();
    void onFailure(Throwable t);
}
