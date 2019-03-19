package userReport;


import Api.ApiService;
import Model.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class UserReportPresenter {

    private UserReportView view;
    private ApiService service;

    public UserReportPresenter(UserReportView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void addReport(String catId, String etc){
        view.showLoading();
        service.addReport(catId, etc)
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
