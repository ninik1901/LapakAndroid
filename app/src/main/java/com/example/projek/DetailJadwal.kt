package com.example.projek

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.projek.app.ApiConfig
import com.example.projek.app.SessionManager
import com.example.projek.databinding.ActivityDetailJadwalBinding
import com.example.projek.model.ModeljadwalLapak
import com.example.projek.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailJadwal : AppCompatActivity() {
    private lateinit var binding: ActivityDetailJadwalBinding
    lateinit var detail: ModeljadwalLapak.Lapak
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        back()
        val intent = intent
        if (intent.hasExtra("detail")) {
            detail = intent.getParcelableExtra("detail")!!
        }
        binding.btnRelawan.setOnClickListener {
            daftarRelawan(detail.id.toString())
        }
    }

    private fun daftarRelawan(lapak_id: String) {
        SessionManager.getIdUser(applicationContext)?.let {
            ApiConfig.instanceRetrofit.up_relawan(
                it,
                lapak_id

            ).enqueue(object :
                Callback<ResponModel> {

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    Toast.makeText(this@DetailJadwal, response.body()?.message, Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
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