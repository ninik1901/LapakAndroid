package com.example.projek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projek.app.ApiConfig
import com.example.projek.app.SessionManager
import com.example.projek.databinding.ActivityInfoDonasiBinding
import com.example.projek.model.ModelInfoDonasiBuku
import com.example.projek.model.ModelInfoDonasiEbook
import com.example.projek.model.ModelInfoPengguna
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoDonasi : AppCompatActivity() {
    private lateinit var binding: ActivityInfoDonasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoDonasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SessionManager.getIdUser(applicationContext)?.let {
            ApiConfig.instanceRetrofit.getDonasipengguna(it).enqueue(object :
                Callback<ModelInfoPengguna> {

                override fun onFailure(call: Call<ModelInfoPengguna>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ModelInfoPengguna>,
                    response: Response<ModelInfoPengguna>
                ) {
                    if (response.isSuccessful) {
                        binding.isiPengguna.text = response.body()!!.Jumlah.toString()
                    }
                }
            })
        }
        ApiConfig.instanceRetrofit.getDonasibuku().enqueue(object :
            Callback<ModelInfoDonasiBuku> {

            override fun onFailure(call: Call<ModelInfoDonasiBuku>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ModelInfoDonasiBuku>,
                response: Response<ModelInfoDonasiBuku>
            ) {

                if (response.isSuccessful) {
                    binding.isiBuku.text = response.body()!!.buku.toString()
                }
            }
        })
        ApiConfig.instanceRetrofit.getInfoEbook().enqueue(object :
            Callback<ModelInfoDonasiEbook> {

            override fun onFailure(call: Call<ModelInfoDonasiEbook>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ModelInfoDonasiEbook>,
                response: Response<ModelInfoDonasiEbook>
            ) {
                if (response.isSuccessful) {
                    binding.isiEbook.text = response.body()!!.ebook.toString()
                }
            }
        })
    }
}
