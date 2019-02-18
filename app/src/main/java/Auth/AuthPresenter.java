package Auth;

import Api.ApiService;
import Model.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter {
    private AuthView view;
    private ApiService service;

    public AuthPresenter(AuthView view, ApiService service){
        this.view = view;
        this.service = service;
    }

    public void login(String email, String password){
        view.showLoading();
        service.login(email,password).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(retrofit2.Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    view.onError();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                view.onFailure(t);
                view.hideLoading();
            }
        });
    }
}
