package com.example.projek.app

import com.example.projek.model.ResponModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama") nama :String,
        @Field("email") email :String,
        @Field("password") password :String,
        @Field("alamat") alamat :String,
        @Field("nomor_telepon") nomor_telepon :String
    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email :String,
        @Field("password") password :String
    ):Call<ResponModel>
}