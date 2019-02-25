package Auth;

import android.content.Intent;
import android.widget.Toast;

import Api.ApiService;
import Model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

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

    public void register(RequestBody identity_no, RequestBody name, RequestBody password, RequestBody email, RequestBody contact, MultipartBody.Part photo_profile, MultipartBody.Part photo_identity, RequestBody worked_status){
        view.showLoading();

        service.registration(identity_no,name,password,email,contact,photo_profile,photo_identity,worked_status)
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
