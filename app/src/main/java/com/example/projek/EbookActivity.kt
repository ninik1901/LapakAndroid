package com.example.projek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projek.adapter.AdapterListEbook
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityEbookBinding
import com.example.projek.model.ResponModelEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EbookActivity : AppCompatActivity() {

    lateinit var binding: ActivityEbookBinding
    private lateinit var adapterListEbook: AdapterListEbook
    private var listEbook: ArrayList<ResponModelEbook.ModelEbook> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiConfig.instanceRetrofit.getAllEbook().enqueue(object : Callback<ResponModelEbook> {
            override fun onFailure(call: Call<ResponModelEbook>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponModelEbook>,
                response: Response<ResponModelEbook>
            ) {
                if (response.isSuccessful) {
                    response.body()?.ebook?.let { listEbook.addAll(it) }
                    adapterListEbook = AdapterListEbook(listEbook, this@EbookActivity)
                    binding.rvLine.apply {
                        layoutManager = GridLayoutManager(this@EbookActivity, 3)
                        adapter = adapterListEbook
                        setHasFixedSize(true)
                    }
                }
            }
        }

        )
    }
}
