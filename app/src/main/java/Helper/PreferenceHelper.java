package Helper;

import android.content.Context;
import android.content.SharedPreferences;

import Model.UserLogin;

public class PreferenceHelper {
    private SharedPreferences sharedPreferences;
    private final String PREFERENCES_NAME="shared_preferences";
    private final String LOGIN="login";
    private final String TOKEN="token";
    private final String NAME="name";


    public PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setLogin(boolean login){
        sharedPreferences.edit()
                .putBoolean(LOGIN,login)
                .apply();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void setName(String name){
        sharedPreferences.edit()
                .putString(NAME,name)
                .apply();
    }

    public String getName(){
        return sharedPreferences.getString(NAME,"");
    }


    public void setToken(String token){
        sharedPreferences.edit().putString(TOKEN,token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN,"");
    }


    public void setLogout(){
        sharedPreferences.edit()
                .clear()
                .apply();
    }

    public void setUserLogin(UserLogin userLogin){
        setLogin(true);
        setToken(userLogin.getToken());
        setName(userLogin.getName());
    }
}
