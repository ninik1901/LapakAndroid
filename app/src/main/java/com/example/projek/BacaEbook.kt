package com.example.projek

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.downloader.PRDownloader
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityBacaEbookBinding
import com.example.projek.model.ResponModel
import com.example.projek.model.ResponModelEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BacaEbook : AppCompatActivity() {

    lateinit var binding: ActivityBacaEbookBinding
    lateinit var pDialog: SweetAlertDialog
    lateinit var detailEbook: ResponModelEbook.ModelEbook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBacaEbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PRDownloader.initialize(applicationContext)
        if (intent.hasExtra("detail")) {
            detailEbook = intent.getParcelableExtra("detail")!!

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
        binding.btnSimpan.setOnClickListener {
            ApiConfig.instanceRetrofit.simpan_buku(
                "13",
                detailEbook.id.toString()

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


