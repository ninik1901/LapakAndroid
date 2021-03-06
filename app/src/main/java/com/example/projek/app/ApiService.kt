package com.example.projek.app

import com.example.projek.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("pengguna/register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("alamat") alamat: String,
        @Field("nomor_telepon") nomor_telepon: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("pengguna/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("fcm_token") fcm_token: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("pengguna/reset")
    fun reset(
        @Field("email") email: String,
        @Field("nomor_telepon") nomor_telepon: String,
        @Field("password") password: String
    ): Call<ResponModel>

    @GET("tampil_ebook")
    fun getAllEbook(): Call<ResponModelEbook>

    @GET("tampil_ebookbaru")
    fun getEbookBaru(): Call<ResponModelEbook>

    @GET("info_donasiebook")
    fun getInfoEbook(): Call<ModelInfoDonasiEbook>

    @GET("info_donasibuku")
    fun getDonasibuku(): Call<ModelInfoDonasiBuku>

    @GET("tampil_lapak")
    fun getJadwallapak(): Call<ModeljadwalLapak>

    @FormUrlEncoded
    @POST("info_donasipengguna")
    fun getDonasipengguna(
        @Field("donatur_id") donatur_id: String
    ): Call<ModelInfoPengguna>

    @FormUrlEncoded
    @POST("tampil_kategoriebook")
    fun getKategoriEbook(
        @Field("kategori") kategori: String
    ): Call<ResponseKategoriEbook>

    @FormUrlEncoded
    @POST("daftar_simpan")
    fun simpan_buku(
        @Field("user_id") user_id: String,
        @Field("buku_id") buku_id: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("upRelawan")
    fun up_relawan(
        @Field("relawan_id") user_id: String,
        @Field("lapakbaca_id") buku_id: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("tambah_baca")
    fun tambah_baca(
        @Field("id_buku") id_buku: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("tampil_simpan")
    fun tampil_simpan(
        @Field("user_id") user_id: String
    ): Call<ModelResponseSimpan>

    @FormUrlEncoded
    @POST("pengajuan_ebook")
    fun pengajuan_ebook(
        @Field("judul_buku") user_id: String,
        @Field("jenis_buku") jenis_buku: String,
        @Field("alamat_donatur") alamat_donatur: String,
        @Field("donatur") donatur: String,
        @Field("sinopsis") sinopsis: String,
        @Field("jenis_donasi") jenis_donasi: String,
        @Field("jumlah_buku") jumlah_buku: String
    ): Call<ResponModel>

    @Multipart
    @POST("pengajuan_buku")
    fun pengajuan_buku(

        @Part("foto_cover\"; filename=\"myfile.jpg\" ") foto_cover: RequestBody?,
        @Part("judul_buku") judul_buku: RequestBody?,
        @Part("jenis_buku") jenis_buku: RequestBody?,
        @Part("alamat_donatur") alamat_donatur: RequestBody?,
        @Part("donatur") donatur: RequestBody?,
        @Part("sinopsis") sinopsis: RequestBody?,
        @Part("jenis_donasi") jenis_donasi: RequestBody?,
        @Part("jumlah_buku") jumlah_buku: RequestBody?
    ): Call<ResponModel>

    @Multipart
    @POST("donasi_ebook")
    fun donasi_ebook(

        @Part("file_ebook\"; filename=\"myfile.pdf\" ") file_ebook: RequestBody?,
        @Part("id_donasi") id_donasi: RequestBody?,
        @Part("judul_buku") judul_buku: RequestBody?
    ): Call<ResponModel>

//    @FormUrlEncoded
//    @POST("donasi_buku")
//    fun donasi_buku(
//        @Field("id_donasi") id_donasi: RequestBody?,
//        @Field("bukti_donasi") bukti_donasi: RequestBody?
//    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("tampil_user")
    fun userInfo(
        @Field("pengguna_id") pengguna_id: String
    ): Call<ModelUserInfo>


    @FormUrlEncoded
    @POST("ubah_profiluser")
    fun update_profil(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("alamat") alamat: String,
        @Field("password") password: String,
        @Field("nomor_telepon") nomor_telepon: String
    ): Call<ResponModel>

    @GET("tampil_jeniskategori")
    fun getKategori(): Call<ResponseKategori>

    @FormUrlEncoded
    @POST("donasi_buku")
    fun donasi_paket(
        @Field("id_donasi") id_donasi: String?,
        @Field("bukti_donasi") bukti_donasi: String?
    ): Call<ResponModel>

    @Multipart
    @POST("donasi_buku")
    fun donasi_cod(
        @Part("id_donasi") id_donasi: RequestBody?,
        @Part("bukti_donasi\"; filename=\"myfile.jpg\" ") bukti_donasi: RequestBody?
    ): Call<ResponModel>


//    @Multipart
//    @POST("/api/Accounts/editaccount")
//    fun editUser(
//        @Header("Authorization") authorization: String?,
//        @Part("file\"; filename=\"pp.png\" ") file: RequestBody?,
//        @Part("FirstName") fname: RequestBody?,
//        @Part("Id") id: RequestBody?
//    ): Call<User?>?

//    @Multipart
//    @POST("/api/receipt/upload")
//    fun uploadReceipt(
//        @Header("Cookie") sessionIdAndRz: String?,
//        @Part file: MultipartBody.Part?,
//        @Part("items") items: RequestBody?,
//        @Part("isAny") isAny: RequestBody?
//    ): Call<List<UploadResult?>?>?
}