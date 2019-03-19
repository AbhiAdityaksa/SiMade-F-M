package utama;

import java.util.List;

import Api.ApiService;
import Model.VillageInfoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtamaPresenter {

    private UtamaView view;
    private ApiService service;

    public UtamaPresenter(UtamaView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void showNews(){
        view.showLoading();
        service.showInfoDesa()
                .enqueue(new Callback<List<VillageInfoResponse>>() {
                    @Override
                    public void onResponse(Call<List<VillageInfoResponse>> call, Response<List<VillageInfoResponse>> response) {
                        if (response.isSuccessful()){
                            view.onSuccess(response.body());
                        }else {
                            view.onError();
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<VillageInfoResponse>> call, Throwable t) {
                        view.onFailure(t);
                        view.hideLoading();
                    }
                });
    }
}
