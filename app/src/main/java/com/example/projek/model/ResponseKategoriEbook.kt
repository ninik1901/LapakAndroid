package com.example.projek.model

import android.os.Parcel
import android.os.Parcelable

class ResponseKategoriEbook {
    lateinit var kategori: List<kategoriEbook>

    data class kategoriEbook(
        val buku_id: Int? = null,
        val foto_cover: String? = null,
        val file_ebook: String? = null,
        val jenis_buku: String? = null,
        val judul_buku: String? = null,
        val jumlah_buku: Int? = null,
        val jumlah_halaman: Int? = null,
        val nama_kategori: String? = null,
        val nama_pengarang: String? = null,
        val penerbit: String? = null,
        val tahun_terbit: String? = null
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(buku_id)
            parcel.writeString(foto_cover)
            parcel.writeString(file_ebook)
            parcel.writeString(jenis_buku)
            parcel.writeString(judul_buku)
            parcel.writeValue(jumlah_buku)
            parcel.writeValue(jumlah_halaman)
            parcel.writeString(nama_kategori)
            parcel.writeString(nama_pengarang)
            parcel.writeString(penerbit)
            parcel.writeString(tahun_terbit)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<kategoriEbook> {
            override fun createFromParcel(parcel: Parcel): kategoriEbook {
                return kategoriEbook(parcel)
            }

            override fun newArray(size: Int): Array<kategoriEbook?> {
                return arrayOfNulls(size)
            }
        }
    }
}