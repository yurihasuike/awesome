package me.myreco.up.CategoryApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {
    @GET("categories/")
    Call<Category> getCategory();
}
