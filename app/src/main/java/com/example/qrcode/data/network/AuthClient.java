package com.example.qrcode.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthClient {
    private static AuthApi authApi;


    public static AuthApi getClient(){
        if (authApi == null){
            authApi = retrofitBuilder();
        }else {
            return authApi;
        }
        return authApi;
    }
    public static AuthApi retrofitBuilder(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AuthApi.class);

    }
}
