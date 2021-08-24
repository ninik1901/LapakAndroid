package com.example.projek

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
                    adapterListLapak = AdapterListLapak(listJadwal, requireActivity())
                    binding.rvJadwalLapak.apply {
                        layoutManager = LinearLayoutManager(requireActivity())
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
        return inflater.inflate(R.layout.activity_jadwal_lapak, container, false)
    }
}
