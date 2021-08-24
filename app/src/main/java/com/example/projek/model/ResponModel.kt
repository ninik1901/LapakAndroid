package com.example.projek.model

class ResponModel {
    var success: Boolean? = null
    var message: String? = ""
    var user: User? = null

    data class User(
        val alamat: String,
        val api_token: Any,
        val created_at: String,
        val email: String,
        val id: Int,
        val nama: String,
        val nomor_telepon: String,
        val role_id: Int,
        val updated_at: String
    )
}