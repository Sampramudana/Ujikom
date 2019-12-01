package com.example.ujikom.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.70.118/idn_uang_santri/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiInterface service = retrofit.create(ApiInterface.class);
}
