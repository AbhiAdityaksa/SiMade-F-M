package utama;

import java.util.List;

import Model.VillageInfoResponse;

public interface UtamaView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<VillageInfoResponse> villageInfoResponseList);
    void onError();
    void onFailure(Throwable t);
}
