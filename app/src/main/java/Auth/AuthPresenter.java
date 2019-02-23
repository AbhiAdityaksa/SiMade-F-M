package Auth;

import android.widget.Toast;

import Api.ApiService;
import Model.User;
import okhttp3.RequestBody;
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

//    public void register(String identity_no, String name, String password, String contact, String photo_profile, String photo_identity, String worked_status){
//        view.showLoading();
//        RequestBody et_identity_no = RequestBody.create(okhttp3.MultipartBody.FORM, identity_no.getBytes().toString());
//        RequestBody et_name = RequestBody.create(okhttp3.MultipartBody.FORM, name.getBytes().toString());
//        RequestBody et_password = RequestBody.create(okhttp3.MultipartBody.FORM, password.getBytes().toString());
//        RequestBody et_contact = RequestBody.create(okhttp3.MultipartBody.FORM, contact.getBytes().toString());
//        RequestBody et_worked_status = RequestBody.create(okhttp3.MultipartBody.FORM, worked_status.getBytes().toString());
//
//
//        service.registration(et_identity_no,et_name,et_password,et_contact,photo_profile,photo_identity,et_worked_status)
//        .enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
//                if(response.isSuccessful()){
//                    view.onSuccess(response.body());
//                }else {
//                    view.onError();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                view.onFailure(t);
//                view.hideLoading();
//            }
//        });
//    }
    }
