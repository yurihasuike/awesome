package me.myreco.up.UserInfoApi;

import me.myreco.up.UserRegistApi.UserRegistResult;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserInfoApi {
    @GET("auth/user/")
    Call<UserInfo> getUserInfo();

    @Multipart
    @PATCH("auth/user/")
    Call<UserInfo> iconPost(@Part("id")RequestBody uid,
                            @Part("icon\"; filename=\"icon_image.png\" ")RequestBody icon_image);

}
