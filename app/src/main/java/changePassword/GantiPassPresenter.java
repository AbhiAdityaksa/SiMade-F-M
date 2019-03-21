package changePassword;

import Api.ApiService;
import Model.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class GantiPassPresenter {

    private GantiPassView view;
    private ApiService service;

    public GantiPassPresenter(GantiPassView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void changePassword(String oldPass, String newPass, String confirmPass){
        view.showLoading();
        service.changePassword(oldPass, newPass, confirmPass)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
