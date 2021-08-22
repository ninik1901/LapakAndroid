package com.example.projek.model

import android.os.Parcel
import android.os.Parcelable

class ResponseKategori {

    lateinit var kategori: List<ModelKategori>

    data class ModelKategori(

        val id: Int? = 0,
        val nama_kategori: String? = null
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(id)
            parcel.writeString(nama_kategori)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ModelKategori> {
            override fun createFromParcel(parcel: Parcel): ModelKategori {
                return ModelKategori(parcel)
            }

            override fun newArray(size: Int): Array<ModelKategori?> {
                return arrayOfNulls(size)
            }
        }
    }


}


