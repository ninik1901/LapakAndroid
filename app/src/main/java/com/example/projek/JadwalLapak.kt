package com.example.projek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projek.adapter.AdapterListLapak
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityJadwalLapakBinding
import com.example.projek.model.ModeljadwalLapak
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JadwalLapak : Fragment() {
    private lateinit var binding: ActivityJadwalLapakBinding
    private lateinit var adapterListLapak: AdapterListLapak
    private var listJadwal: ArrayList<ModeljadwalLapak.Lapak> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityJadwalLapakBinding.bind(view)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
