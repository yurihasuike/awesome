package me.myreco.up.PostApi;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class ImgPost {
    @SerializedName("body")
    String body;

    @SerializedName("category")
    Integer category_id;

    @SerializedName("img")
    byte[] img;

    public ImgPost(String body, Integer category_id, byte[] img){
        this.body = body;
        this.category_id = category_id;
        this.img = img;
    }
}
