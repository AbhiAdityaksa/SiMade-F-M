package Auth;

import Api.ApiService;
import Model.User;
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
        service.login(email,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    view.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onFailure(t);
                view.hideLoading();
            }
        });
    }

    public void register(String identity_no, String name, String password, String contact, String photo_profile, String photo_identity, String worked_status){
        view.showLoading();
        service.registration(identity_no,name,password,contact,photo_profile,photo_identity,worked_status)
        .enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    view.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onFailure(t);
                view.hideLoading();
            }
        });
    }
}
