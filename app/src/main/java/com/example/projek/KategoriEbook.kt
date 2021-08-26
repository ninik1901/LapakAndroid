package com.example.projek

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projek.adapter.AdapterKategoriEbook
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityKategoriEbookBinding
import com.example.projek.model.ResponseKategori
import com.example.projek.model.ResponseKategoriEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriEbook : AppCompatActivity() {
    private lateinit var binding: ActivityKategoriEbookBinding
    lateinit var kategori: ResponseKategori.ModelKategori
    lateinit var adapternya: AdapterKategoriEbook
    var listKategori: ArrayList<ResponseKategoriEbook.kategoriEbook> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriEbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("kategori")) {
            kategori = intent.getParcelableExtra("kategori")!!
        }
        getKategoriEbook(kategori.id.toString())
        back()
    }

    fun getKategoriEbook(kategori: String) {
        ApiConfig.instanceRetrofit.getKategoriEbook(kategori)
            .enqueue(object : Callback<ResponseKategoriEbook> {
                override fun onFailure(call: Call<ResponseKategoriEbook>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseKategoriEbook>,
                    response: Response<ResponseKategoriEbook>
                ) {
                    if (response.isSuccessful) {
                        response.body()!!.kategori.let { listKategori.addAll(it) }
                        adapternya = AdapterKategoriEbook(listKategori, this@KategoriEbook)
                        binding.rvEbook.apply {
                            layoutManager = LinearLayoutManager(this@KategoriEbook)
                            adapter = adapternya
                        }
                    }
                }
            }

            )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun back() {
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { v: View? -> onBackPressed() }
    }
}