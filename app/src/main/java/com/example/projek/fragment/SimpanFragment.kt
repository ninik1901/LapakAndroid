package com.example.projek.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projek.R
import com.example.projek.adapter.AdapterListSimpan
import com.example.projek.app.ApiConfig
import com.example.projek.app.SessionManager
import com.example.projek.databinding.FragmentSimpanBinding
import com.example.projek.model.ModelResponseSimpan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SimpanFragment : Fragment() {
    private lateinit var binding: FragmentSimpanBinding
    private var listSimpan: ArrayList<ModelResponseSimpan.ModelSimpan> = ArrayList()
    private lateinit var adapterListSimpan: AdapterListSimpan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simpan, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSimpanBinding.bind(view)
        getSimpan()

    }

    fun getSimpan() {
        SessionManager.getIdUser(requireActivity())?.let {
            ApiConfig.instanceRetrofit.tampil_simpan(
                it
            )
                .enqueue(object : Callback<ModelResponseSimpan> {
                    override fun onFailure(call: Call<ModelResponseSimpan>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<ModelResponseSimpan>,
                        response: Response<ModelResponseSimpan>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.tampil?.let { listSimpan.addAll(it) }
                            adapterListSimpan =
                                activity?.let { AdapterListSimpan(listSimpan, it) }!!
                            binding.rvSimpan.apply {

                                layoutManager =
                                    LinearLayoutManager(activity)
                                adapter = adapterListSimpan

                            }
                        }
                    }
                }

                )
        }
    }

}
