package Api;

import java.util.List;

import Model.HistoryResponse;
import Model.ReportCategory;
import Model.Response;
import Model.User;
import Model.VillageInfoResponse;
import Model.VillagerWorkerAdmin;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<User>login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("register")
    Call<User>registration(@Part MultipartBody.Part photo_identity,
                            @Part("identity_no") RequestBody identity_no,
                            @Part("name") RequestBody name,
                            @Part("password") RequestBody password,
                            @Part("email") RequestBody email,
                            @Part("contact") RequestBody contact,
                            @Part("worked_status") RequestBody worked_status);

    @GET("admin/show/villager/worked")
    Call<VillagerWorkerAdmin> villagerWorked();

    @GET("show/report-category")
    Call<List<ReportCategory>> showReportCategory();

    @GET("admin/show/history")
    Call<List<HistoryResponse>> showHistory();

    @GET("show/info")
    Call<List<VillageInfoResponse>> showInfoDesa();

    @Multipart
    @POST("admin/add/info")
    Call<Response> addInfo(
            @Part MultipartBody.Part picture,
            @Part("name") RequestBody name,
            @Part("etc") RequestBody etc,
            @Part("category_id") RequestBody category_id
    );

    @Multipart
    @POST("admin/add/info")
    Call<Response> addInfoSumbangan(
            @Part MultipartBody.Part picture,
            @Part("name") RequestBody name,
            @Part("etc") RequestBody etc,
            @Part("category_id") RequestBody category_id,
            @Part("valid") RequestBody valid
    );
}
