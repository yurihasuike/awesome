package me.myreco.up.PostApi;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostApi {
    @GET("posts/")
    Call<Post> getPost();

    @Multipart
    @POST("posts/")
    Call<Result> imgPost(
            @Part("body") RequestBody body,
            @Part("category") RequestBody category_id,
            @Part("img\"; filename=\"image.png\" ") RequestBody file
    );

}

