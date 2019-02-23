package Api;

import com.example.simadeui.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static ApiService getService(MainActivity mainActivity){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simade.itcc-udayana.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
