package me.myreco.up.UserRegistApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRegistApi {
    @POST("auth/registration/")
    Call<UserRegistResult> regist(@Body UserRegist userRegist);
}
