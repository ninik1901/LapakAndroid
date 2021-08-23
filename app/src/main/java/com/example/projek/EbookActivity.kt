package com.example.projek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projek.adapter.AdapterListEbook
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityEbookBinding
import com.example.projek.model.ResponModelEbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EbookActivity : Fragment() {

    lateinit var binding: ActivityEbookBinding
    private lateinit var adapterListEbook: AdapterListEbook
    private var listEbook: ArrayList<ResponModelEbook.ModelEbook> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityEbookBinding.bind(view)

        ApiConfig.instanceRetrofit.getAllEbook().enqueue(object : Callback<ResponModelEbook> {
            override fun onFailure(call: Call<ResponModelEbook>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponModelEbook>,
                response: Response<ResponModelEbook>
            ) {
                if (response.isSuccessful) {
                    response.body()?.ebook?.let { listEbook.addAll(it) }
                    adapterListEbook = AdapterListEbook(listEbook,requireActivity())
                    binding.rvLine.apply {
                        layoutManager = GridLayoutManager(requireActivity(),3)
                        adapter = adapterListEbook
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
            return inflater.inflate(R.layout.activity_ebook, container, false)
    }
}
