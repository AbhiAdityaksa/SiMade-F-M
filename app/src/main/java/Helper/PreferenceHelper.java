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
    private final String NOKTP = "noktp";
    private final String EMAIL = "email";
    private final String CONTACT = "contact";
    private final String WORKED = "worked";
    private final String BIRTHDAY = "birthday";
    private final String PROFILE = "profile";

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

    public void setNoktp(String noktp){
        sharedPreferences.edit().putString(NOKTP, noktp).apply();
    }

    public String getNoktp() {
        return sharedPreferences.getString(NOKTP, "");
    }

    public void setEmail(String email){
        sharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setContact(String contact){
        sharedPreferences.edit().putString(CONTACT, contact).apply();
    }

    public String getContact() {
        return sharedPreferences.getString(CONTACT, "");
    }

    public void setWorked(String worked){
        sharedPreferences.edit().putString(WORKED, worked).apply();
    }

    public String getWorked() {
        return sharedPreferences.getString(WORKED, "");
    }

    public void setBirthday(String birthday){
        sharedPreferences.edit().putString(BIRTHDAY, birthday).apply();
    }

    public String getBirthday() {
        return sharedPreferences.getString(BIRTHDAY, "");
    }

    public void setProfile(String profile){
        sharedPreferences.edit().putString(PROFILE, profile).apply();
    }

    public String getProfile() {
        return sharedPreferences.getString(PROFILE, "");
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
        setNoktp(user.getIdentityNo());
        setEmail(user.getEmail());
        setContact(user.getContact());
    }
}
