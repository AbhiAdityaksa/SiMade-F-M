package Api;

import Model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

}
