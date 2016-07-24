package me.myreco.up.LoginApi;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("email_or_username")
    String email_or_username;

    @SerializedName("password")
    String password;

    public Login(String email_or_username, String password) {
        this.email_or_username = email_or_username;
        this.password = password;
    }
}
