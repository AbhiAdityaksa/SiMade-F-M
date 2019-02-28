package Helper;

import android.content.Context;
import android.content.SharedPreferences;

import Model.User;

public class PreferenceHelper {
    private SharedPreferences sharedPreferences;
    private final String PREFERENCES_NAME="shared_preferences";
    private final String LOGIN="login";
    private final String TOKEN="token";
    private final String NAME="name";
    private final String ID ="id";
    private final String WORK ="work";
    private final String PERMISSION = "permission";

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

    public void setId(Integer id){
        sharedPreferences.edit()
                .putInt(ID,id)
                .apply();
    }

    public String getId(){
        return sharedPreferences.getString(ID,"");
    }

    public void setWork(String work){
        sharedPreferences.edit()
                .putString(WORK,work)
                .apply();
    }

    public String getWork(){
        return sharedPreferences.getString(WORK,"");
    }


    public void setToken(String token){
        sharedPreferences.edit().putString(TOKEN,token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN,"");
    }

    public void setPermission(String permission){
        sharedPreferences.edit().putString(PERMISSION, permission).apply();
    }

    public String getPermission() {
        return sharedPreferences.getString(PERMISSION, "");
    }

    public void setLogout(){
        sharedPreferences.edit()
                .clear()
                .apply();
    }

    public void setUser(User user){
        setLogin(true);
        setToken(user.getToken());
        setName(user.getName());
        setId(user.getId());
        setWork(user.getWorkedStatus());
        setPermission(user.getPermission());
    }
}
