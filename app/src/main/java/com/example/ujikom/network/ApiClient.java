package com.example.ujikom.network;

import android.widget.Toast;

import com.example.ujikom.Util.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.70.90/idn_uang_santri/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiInterface service = retrofit.create(ApiInterface.class);
}
