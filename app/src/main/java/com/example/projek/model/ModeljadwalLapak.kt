package com.example.projek.model

import android.os.Parcel
import android.os.Parcelable

class ModeljadwalLapak {
    lateinit var lapak: List<Lapak>

    data class Lapak(
        val created_at: String? = null,
        val id: Int? = null,
        val jumlah_relawan: Int? = null,
        val latitude: String? = null,
        val lokasi: String? = null,
        val longitude: String? = null,
        val nama_kegiatan: String? = null,
        val tanggal: String? = null,
        val updated_at: String? = null,
        val user_id: Any
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("user_id")
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(created_at)
            parcel.writeValue(id)
            parcel.writeValue(jumlah_relawan)
            parcel.writeString(latitude)
            parcel.writeString(lokasi)
            parcel.writeString(longitude)
            parcel.writeString(nama_kegiatan)
            parcel.writeString(tanggal)
            parcel.writeString(updated_at)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Lapak> {
            override fun createFromParcel(parcel: Parcel): Lapak {
                return Lapak(parcel)
            }

            override fun newArray(size: Int): Array<Lapak?> {
                return arrayOfNulls(size)
            }
        }
    }
}
