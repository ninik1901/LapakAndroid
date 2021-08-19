package com.example.projek.model

import android.os.Parcel
import android.os.Parcelable

data class ModelUserInfo(
    val alamat: String? = null,
    val api_token: String? = null,
    val created_at: String? = null,
    val email: String? = null,
    val id: Int? = null,
    val nama: String? = null,
    val nomor_telepon: String? = null,
    val role_id: Int? = null,
    val updated_at: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(alamat)
        parcel.writeString(api_token)
        parcel.writeString(created_at)
        parcel.writeString(email)
        parcel.writeValue(id)
        parcel.writeString(nama)
        parcel.writeString(nomor_telepon)
        parcel.writeValue(role_id)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelUserInfo> {
        override fun createFromParcel(parcel: Parcel): ModelUserInfo {
            return ModelUserInfo(parcel)
        }

        override fun newArray(size: Int): Array<ModelUserInfo?> {
            return arrayOfNulls(size)
        }
    }
}
