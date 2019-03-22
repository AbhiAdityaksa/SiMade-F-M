package Api;

import java.util.List;

import Model.DetailSumCarityResponse;
import Model.HistoryResponse;
import Model.ReportCategory;
import Model.Response;
import Model.SumCarityResponse;
import Model.User;
import Model.VillageInfoResponse;
import Model.VillagerWorkerAdmin;
import Model.WorkerResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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

    @FormUrlEncoded
    @POST("add/question")
    Call<Response>addQuestion(@Field("question") String question, @Field("village_info_id") Integer village_info_id);

    @FormUrlEncoded
    @POST("change/password")
    Call<Response>changePassword(@Field("passwordold") String passwordold, @Field("password") String password, @Field("password_confirmation") String password_confirmation);

    @GET("admin/show/villager/worked")
    Call<VillagerWorkerAdmin> villagerWorked();

    @GET("show/report-category")
    Call<List<ReportCategory>> showReportCategory();

    @GET("show/report-category")
    Call<List<Response>> catReport();

    @GET("admin/show/history")
    Call<List<HistoryResponse>> showHistory();

    @GET("show/info")
    Call<List<VillageInfoResponse>> showInfoDesa();

    @GET("admin/show/info-category")
    Call<List<Response>> catInfo();

    @GET("admin/show/total/carity")
    Call<List<SumCarityResponse>> showSumCarity();

    @GET("admin/show/carity/detail/{id}")
    Call<List<DetailSumCarityResponse>> showListPenyumbang(@Path("id") int id);

    @GET("show/all/worker")
    Call<List<WorkerResponse>> showAllWorker();

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

    @FormUrlEncoded
    @POST("add/report")
    Call<Response> addReport(@Field("report_category_id") String reportCat,
                             @Field("etc") String etc);
}
