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

    public void register(RequestBody no_ktp, RequestBody nama, RequestBody pass, RequestBody mail, RequestBody kontak, MultipartBody.Part pp, MultipartBody.Part ktp, RequestBody status_kerja){
        view.showLoading();

        service.registration(no_ktp,nama,pass,mail,kontak,pp, ktp, status_kerja)
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
