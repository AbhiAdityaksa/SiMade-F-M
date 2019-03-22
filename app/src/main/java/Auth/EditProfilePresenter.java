package Auth;

import java.util.List;

import Api.ApiService;
import Model.WorkerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter {
    private EditProfileView view;
    private ApiService service;

    public EditProfilePresenter(EditProfileView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showAllWorker(){
        view.showLoading();
        service.showAllWorker()
                .enqueue(new Callback<List<WorkerResponse>>() {
                    @Override
                    public void onResponse(Call<List<WorkerResponse>> call, Response<List<WorkerResponse>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<WorkerResponse>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
