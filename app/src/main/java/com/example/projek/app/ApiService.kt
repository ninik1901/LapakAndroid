package com.example.projek.app

import com.example.projek.model.ResponModel
import com.example.projek.model.ResponModelEbook
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("alamat") alamat: String,
        @Field("nomor_telepon") nomor_telepon: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponModel>

    @GET("tampil_ebook")
    fun getAllEbook(): Call<ResponModelEbook>

    @FormUrlEncoded
    @POST("daftar_simpan")
    fun simpan_buku(
        @Field("user_id") user_id: String,
        @Field("buku_id") buku_id: String
    ): Call<ResponModel>
}