package me.myreco.up;

import android.content.Context;
import android.content.SharedPreferences;

public class SavePreference {
    public void save_aToken(Context context, String aToken) {
        SharedPreferences pref = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("aToken", aToken);
        editor.commit();
    }

    public String load_aToken(Context context){
        SharedPreferences pref = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);
        return pref.getString("aToken", null);
    }

    public void save_username(Context context, String user_name){
        SharedPreferences pref = context.getSharedPreferences("user_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_name", user_name);
        editor.commit();
    }

    public String load_username(Context context){
        SharedPreferences pref = context.getSharedPreferences("user_name", Context.MODE_PRIVATE);
        return  pref.getString("user_name", null);
    }
}
