package Auth;

import Model.User;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void onSuccess(User user);
    void onError();
    void onFailure(Throwable t);
}
