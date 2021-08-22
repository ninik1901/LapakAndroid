package com.example.projek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projek.adapter.AdapterListLapak
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityJadwalLapakBinding
import com.example.projek.model.ModeljadwalLapak
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JadwalLapak : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalLapakBinding
    private lateinit var adapterListLapak: AdapterListLapak
    private var listJadwal: ArrayList<ModeljadwalLapak.Lapak> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalLapakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ApiConfig.instanceRetrofit.getJadwallapak().enqueue(object :
            Callback<ModeljadwalLapak> {

            override fun onFailure(call: Call<ModeljadwalLapak>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ModeljadwalLapak>,
                response: Response<ModeljadwalLapak>
            ) {

                if (response.isSuccessful) {
                    response.body()?.lapak?.let { listJadwal.addAll(it) }
                    adapterListLapak = AdapterListLapak(listJadwal, this@JadwalLapak)
                    binding.rvJadwalLapak.apply {
                        layoutManager = LinearLayoutManager(this@JadwalLapak)
                        adapter = adapterListLapak
                        setHasFixedSize(true)
                    }

                }
            }
        })
    }
}