package Api;

import android.content.Context;

import java.io.IOException;

import Helper.ConstantURL;
import Helper.PreferenceHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static ApiService getService(Context context){
        final PreferenceHelper preferenceHelper = new PreferenceHelper(context);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request;
                        if (preferenceHelper.getLogin()){
                            request=chain
                                    .request()
                                    .newBuilder()
                                    .addHeader("Context-Type", "application/json")
                                    .addHeader("Authorization", "Bearer "+preferenceHelper.getToken())
                                    .build();
                        } else {
                            request=chain
                                    .request()
                                    .newBuilder()
                                    .addHeader("Context-Type", "application/json")
                                    .build();
                        }
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantURL.URL.api())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }
}
