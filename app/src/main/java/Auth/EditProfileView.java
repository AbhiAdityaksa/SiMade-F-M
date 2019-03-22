package Auth;

import java.util.List;

import Model.WorkerResponse;

public interface EditProfileView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<WorkerResponse> workerResponses);
    void onError();
    void onFailure(Throwable t);
}
