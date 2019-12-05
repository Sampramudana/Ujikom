package com.example.ujikom.network;

import com.example.ujikom.model.getKelas.ResponseGetKelas;
import com.example.ujikom.model.getAdminSantri.ResponseGetAllSantri;
import com.example.ujikom.model.getSantri.ResponseGetSantri;
import com.example.ujikom.model.getuang.ResponseGetUang;
import com.example.ujikom.model.login.ResponseLogin;
import com.example.ujikom.model.register.ResponseRegister;
import com.example.ujikom.model.tambahKelas.ResponseTambahKelas;
import com.example.ujikom.model.tambahSantri.ResponseTambahSantri;
import com.example.ujikom.model.transaksi.ResponseTransaksi;
import com.example.ujikom.model.updateUser.ResponseUpdateUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
    Call<ArrayList<ResponseGetSantri>> responseGetSantri(@Query("kelas") String kelas);

    // TODO Update User
    @PUT("update_user.php")
    Call<ResponseUpdateUser> responseUpdateUser(@Query("id_user") String id_user,
                                                @Query("nama") String nama,
                                                @Query("vsusername") String vsusername,
                                                @Query("vspassword") String vspassword,
                                                @Query("kelas") String kelas);

    // TODO Get Semua Santri
    @GET("get_all_santri.php")
    Call<ArrayList<ResponseGetAllSantri>> responseGetAllSantri();

    // TODO Get Kelas
    @GET("get_kelas.php")
    Call<ArrayList<ResponseGetKelas>> responseGetKelas();

    // TODO Tambah Siswa
    @FormUrlEncoded
    @POST("insert_kelas.php")
    Call<ResponseTambahKelas> responseTambahKelas(@Field("kelas") String kelas);

    // TODO Get Uang
    @GET("get_uang_by_id.php")
    Call<ArrayList<ResponseGetUang>> responseGetUang(@Query("id_santri") String id_santri);

    // TODO Transaksi
    @FormUrlEncoded
    @POST("transaksi.php")
    Call<ResponseTransaksi> responseTransaksi(@Field("uang") String uang,
                                              @Field("id_santri") String id_santri,
                                              @Field("status_uang") String status_uang,
                                              @Field("selisih_uang") String selisih_uang,
                                              @Field("keterangan") String keterangan);
}
