package Auth;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import Api.ApiService;
import Model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

import static android.support.constraint.Constraints.TAG;

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
//                    Log.d(TAG, "LOGIN: "+ user.getId());
                }else {

                    view.onError(response.body());
//                    Log.d(TAG, "LOGIN: "+ user.getSuccess());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onFailure(t);
                Log.d(TAG, "onFailure: "+t);
                view.hideLoading();
            }
        });
    }

    public void register(MultipartBody.Part photo_identity, RequestBody identity_no, RequestBody name, RequestBody password, RequestBody email, RequestBody contact, RequestBody worked_status){
        view.showLoading();
        service.registration(photo_identity,identity_no,name,password,email,contact, worked_status)
        .enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    view.onSuccess(response.body());

                }else {
                    view.onError(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                view.onFailure(t);
//                view.hideLoading();
            }
        });
    }
}
