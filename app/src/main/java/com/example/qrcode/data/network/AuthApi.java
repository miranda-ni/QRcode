package com.example.qrcode.data.network;

import com.example.qrcode.data.models.AuthModels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AuthApi {
    @GET("user")
    Call<AuthModels>getUser(@Header("Authorization")String id);
}
