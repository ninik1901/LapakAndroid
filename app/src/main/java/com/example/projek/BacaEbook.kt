package com.example.projek

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.downloader.PRDownloader
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityBacaEbookBinding
import com.example.projek.model.ModelResponseSimpan
import com.example.projek.model.ResponModel
import com.example.projek.model.ResponModelEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BacaEbook : AppCompatActivity() {

    lateinit var binding: ActivityBacaEbookBinding
    lateinit var pDialog: SweetAlertDialog
    lateinit var detailEbook: ResponModelEbook.ModelEbook
    lateinit var detailEbookSimpan: ModelResponseSimpan.ModelSimpan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBacaEbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PRDownloader.initialize(applicationContext)
        if (intent.hasExtra("detail")) {
            detailEbook = intent.getParcelableExtra("detail")!!
            Glide.with(this@BacaEbook)
                .load("https://ninik.panjisastra.my.id/img/buku/" + detailEbook.foto_cover)
                .fitCenter()
                .dontAnimate()
                .into(binding.detailGambar)
            binding.detailJudul.text = detailEbook.judul_buku
            binding.detailNamaPengarang.text = detailEbook.nama_pengarang
            binding.detailJumlahBaca.text = detailEbook.jumlah_baca.toString()
            binding.btnBaca.setOnClickListener {
                ApiConfig.instanceRetrofit.tambah_baca(
                    detailEbook.id.toString()

                ).enqueue(object :
                    Callback<ResponModel> {

                    override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponModel>,
                        response: Response<ResponModel>
                    ) {
                        Toast.makeText(this@BacaEbook, response.body()?.message, Toast.LENGTH_LONG)
                            .show()
                    }
                })
                val i = Intent(this, Pdf::class.java)
                i.putExtra("pdf", detailEbook)
                startActivity(i)
            }
        }
        if (intent.hasExtra("simpan")) {
            detailEbookSimpan = intent.getParcelableExtra("simpan")!!
            Glide.with(this@BacaEbook)
                .load("https://ninik.panjisastra.my.id/img/buku/" + detailEbookSimpan.foto_cover)
                .fitCenter()
                .dontAnimate()
                .into(binding.detailGambar)
            binding.detailJudul.text = detailEbookSimpan.judul_buku
            binding.detailNamaPengarang.text = detailEbookSimpan.nama_pengarang
            binding.detailJumlahBaca.text = detailEbookSimpan.jumlah_baca.toString()
            binding.btnBaca.setOnClickListener {
                ApiConfig.instanceRetrofit.tambah_baca(
                    detailEbookSimpan.id.toString()

                ).enqueue(object :
                    Callback<ResponModel> {

                    override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponModel>,
                        response: Response<ResponModel>
                    ) {
                        Toast.makeText(this@BacaEbook, response.body()?.message, Toast.LENGTH_LONG)
                            .show()
                    }
                })
                val i = Intent(this, Pdf::class.java)
                i.putExtra("pdf", detailEbookSimpan)
                startActivity(i)
            }
        }
        binding.btnSimpan.setOnClickListener {
            ApiConfig.instanceRetrofit.simpan_buku(
                "13",
                detailEbookSimpan.id.toString()

            ).enqueue(object :
                Callback<ResponModel> {

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    Toast.makeText(this@BacaEbook, response.body()?.message, Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }
}


