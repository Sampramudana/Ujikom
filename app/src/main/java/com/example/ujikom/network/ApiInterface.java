package com.example.ujikom.network;

import com.example.ujikom.model.getsantri.ResponseGetSantri;
import com.example.ujikom.model.login.ResponseLogin;
import com.example.ujikom.model.register.ResponseRegister;
import com.example.ujikom.model.tambahsantri.ResponseTambahSantri;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    // TODO Tambah Siswa
    @FormUrlEncoded
    @POST("insert_santri.php")
    Call<ResponseTambahSantri> responseTambahSantri(@Field("nama_santri") String nama,
                                                    @Field("kelas") String kelas);

    // TODO Get Santri
    @GET("get_santri.php")
    Call<ArrayList<ResponseGetSantri>> responseGetSantri();
}
