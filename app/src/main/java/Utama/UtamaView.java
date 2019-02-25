package Utama;

import Model.News;

public interface UtamaView {
    void showLoading();
    void hideLoading();
    void onSuccess(News news);
    void onError();
    void onFailure(Throwable t);

}
