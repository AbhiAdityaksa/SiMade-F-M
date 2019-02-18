package Api;

import Model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("registration")
    Call<User> registration(@Part("identity_no") String identity_no,
                                        @Part("name") String name,
                                        @Part("password") String password,
                                        @Part("contact") String contact,
                                        @Part("photo_profile") String photo_profile,
                                        @Part("photo_identity") String photo_identity,
                                        @Part("worked_status") String worked_status);

}
