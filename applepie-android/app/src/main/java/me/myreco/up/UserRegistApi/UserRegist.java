package me.myreco.up.UserRegistApi;

import com.google.gson.annotations.SerializedName;

public class UserRegist {
    @SerializedName("username")
    String username;

    @SerializedName("password1")
    String password1;

    @SerializedName("password2")
    String password2;

    @SerializedName("email")
    String email;

    @SerializedName("nickname")
    String nickname;

    public UserRegist(String username, String password, String email, String nickname) {
        this.username = username;
        this.password1 = password;
        this.password2 = password;
        this.email = email;
        this.nickname = nickname;
    }
}
