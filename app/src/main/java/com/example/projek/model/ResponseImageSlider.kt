package com.example.projek.model

import android.os.Parcel
import android.os.Parcelable

class ResponseImageSlider {

    lateinit var ebook: List<ModelImageSlider>

    data class ModelImageSlider(
        val created_at: String? = null,
        val donatur_id: Int? = null,
        val file_ebook: String? = null,
        val foto_cover: String? = null,
        val id: Int? = null,
        val jenis_buku: String? = null,
        val judul_buku: String? = null,
        val jumlah_baca: Int? = null,
        val jumlah_buku: Int? = null,
        val jumlah_halaman: Int? = null,
        val kategori_id: Int? = null,
        val nama_pengarang: String? = null,
        val penerbit: String? = null,
        val sinopsis: String? = null,
        val tahun_terbit: String? = null,
        val updated_at: String? = null
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(created_at)
            parcel.writeValue(donatur_id)
            parcel.writeString(file_ebook)
            parcel.writeString(foto_cover)
            parcel.writeValue(id)
            parcel.writeString(jenis_buku)
            parcel.writeString(judul_buku)
            parcel.writeValue(jumlah_baca)
            parcel.writeValue(jumlah_buku)
            parcel.writeValue(jumlah_halaman)
            parcel.writeValue(kategori_id)
            parcel.writeString(nama_pengarang)
            parcel.writeString(penerbit)
            parcel.writeString(sinopsis)
            parcel.writeString(tahun_terbit)
            parcel.writeString(updated_at)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ModelImageSlider> {
            override fun createFromParcel(parcel: Parcel): ModelImageSlider {
                return ModelImageSlider(parcel)
            }

            override fun newArray(size: Int): Array<ModelImageSlider?> {
                return arrayOfNulls(size)
            }
        }
    }
}