package com.example.ujikom.network;

import com.example.ujikom.model.login.ResponseLogin;
import com.example.ujikom.model.register.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    // TODO Login
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> responseLogin(@Field("vsusername") String vsusername,
                                      @Field("vspassword") String vspassword);

    // TODO Register
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> responseRegister(@Field("nama") String nama,
                                            @Field("kelas") String kelas,
                                            @Field("vsusername") String vsusername,
                                            @Field("vspassword") String vspassword);
}
