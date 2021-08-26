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
import com.example.projek.model.ResponseKategoriEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BacaEbook : AppCompatActivity() {

    lateinit var binding: ActivityBacaEbookBinding
    lateinit var pDialog: SweetAlertDialog
    lateinit var detailEbook: ResponModelEbook.ModelEbook
    lateinit var detailEbookSimpan: ModelResponseSimpan.ModelSimpan
    lateinit var detailKategori: ResponseKategoriEbook.kategoriEbook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBacaEbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PRDownloader.initialize(applicationContext)
        if (intent.hasExtra("detail")) {
            detailEbook = intent.getParcelableExtra("detail")!!
            Glide.with(this@BacaEbook)
                .load("https://ta.poliwangi.ac.id/~ti18099/public/img/buku/" + detailEbook.foto_cover)
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
            binding.btnSimpan.setOnClickListener {
                com.example.projek.app.SessionManager.getIdUser(applicationContext)?.let { it1 ->
                    ApiConfig.instanceRetrofit.simpan_buku(
                        it1,
                        detailEbook.id.toString()

                    ).enqueue(object :
                        Callback<ResponModel> {

                        override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponModel>,
                            response: Response<ResponModel>
                        ) {
                            Toast.makeText(
                                this@BacaEbook,
                                response.body()?.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    })
                }
            }
        }
        if (intent.hasExtra("simpan")) {
            detailEbookSimpan = intent.getParcelableExtra("simpan")!!
            Glide.with(this@BacaEbook)
                .load("https://ta.poliwangi.ac.id/~ti18099/public/img/buku/" + detailEbookSimpan.foto_cover)
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
                i.putExtra("pdfSimpan", detailEbookSimpan)
                startActivity(i)
            }
            binding.btnSimpan.setOnClickListener {
                com.example.projek.app.SessionManager.getIdUser(applicationContext)?.let { it1 ->
                    ApiConfig.instanceRetrofit.simpan_buku(
                        it1,
                        detailEbookSimpan.id.toString()

                    ).enqueue(object :
                        Callback<ResponModel> {

                        override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponModel>,
                            response: Response<ResponModel>
                        ) {
                            Toast.makeText(
                                this@BacaEbook,
                                response.body()?.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    })
                }
            }
        }
        if (intent.hasExtra("kategori")) {
            detailKategori = intent.getParcelableExtra("kategori")!!
            Glide.with(this@BacaEbook)
                .load("https://ta.poliwangi.ac.id/~ti18099/public/img/buku/" + detailKategori.foto_cover)
                .fitCenter()
                .dontAnimate()
                .into(binding.detailGambar)
            binding.detailJudul.text = detailKategori.judul_buku
            binding.detailNamaPengarang.text = detailKategori.nama_pengarang
//            binding.detailJumlahBaca.text = detailKategori..toString()
            binding.btnBaca.setOnClickListener {
                ApiConfig.instanceRetrofit.tambah_baca(
                    detailKategori.buku_id.toString()

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
                i.putExtra("pdfKategori", detailKategori)
                startActivity(i)
            }
            binding.btnSimpan.setOnClickListener {
                com.example.projek.app.SessionManager.getIdUser(applicationContext)?.let { it1 ->
                    ApiConfig.instanceRetrofit.simpan_buku(
                        it1,
                        detailKategori.buku_id.toString()

                    ).enqueue(object :
                        Callback<ResponModel> {

                        override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponModel>,
                            response: Response<ResponModel>
                        ) {
                            Toast.makeText(
                                this@BacaEbook,
                                response.body()?.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    })
                }
            }
        }

    }
}