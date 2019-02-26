package Auth;

import Model.Response;
import Model.User;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void onSuccess(User user);
    void onError(User user);
    void onFailure(Throwable t);
}
