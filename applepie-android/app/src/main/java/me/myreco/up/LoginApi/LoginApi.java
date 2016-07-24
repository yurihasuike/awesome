package me.myreco.up.LoginApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("auth/login/")
    Call<LoginResult> login(@Body Login login);
}

