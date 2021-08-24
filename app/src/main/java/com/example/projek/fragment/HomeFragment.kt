package com.example.projek.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.projek.R
import com.example.projek.adapter.AdapterImageSlider
import com.example.projek.adapter.AdapterListEbook
import com.example.projek.adapter.AdapterListKategori
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.FragmentHomeBinding
import com.example.projek.model.ResponModelEbook
import com.example.projek.model.ResponseKategori
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvEbook: RecyclerView
    lateinit var binding: FragmentHomeBinding
    private lateinit var adapterListEbook: AdapterListEbook
    private lateinit var adapterListKategori: AdapterListKategori
    lateinit var adapterImageSlider: AdapterImageSlider
    private var listEbook: ArrayList<ResponModelEbook.ModelEbook> = ArrayList()
    private var listKategori: ArrayList<ResponseKategori.ModelKategori> = ArrayList()
    var listImage: ArrayList<ResponModelEbook.ModelEbook> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

//        vpSlider = view.findViewById(R.id.vp_slider)
//        rvEbook = view.findViewById(R.id.rv_Ebook)
//        listImage.add(ModelImageSlider("https://cdn.pixabay.com/photo/2016/10/14/16/38/book-1740512_960_720.png"))
//        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp4664615.jpg"))
//        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp8483511.jpg"))
//        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp1850842.png"))
//        listImage.add(ModelImageSlider("https://wallpapercave.com/wp/wp8483514.png"))
//
//        val arraySlider= ArrayList<Int>()
//        arraySlider.add(R.drawable.buku1)
//        arraySlider.add(R.drawable.buku2)
//        arraySlider.add(R.drawable.buku3)
//
//        val adapterSlider = AdapterSlider(arraySlider, activity)
//        vpSlider.adapter = adapterSlider
//
//        val layoutManager = LinearLayoutManager(activity)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//
//        rvEbook.adapter= AdapterEbook(arrayEbook)
//        rvEbook.layoutManager = layoutManager
//


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        getEbook()
        getKategori()
        getEbookBaru()



    }

    fun getEbook() {
        ApiConfig.instanceRetrofit.getAllEbook().enqueue(object : Callback<ResponModelEbook> {
            override fun onFailure(call: Call<ResponModelEbook>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponModelEbook>,
                response: Response<ResponModelEbook>
            ) {
                if (response.isSuccessful) {
                    response.body()?.ebook?.let { listEbook.addAll(it) }
                    adapterListEbook = activity?.let { AdapterListEbook(listEbook, it) }!!
                    binding.rvEbook.apply {

                        layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = adapterListEbook

                    }
                }
            }
        }

        )
    }

    fun getEbookBaru() {
        ApiConfig.instanceRetrofit.getEbookBaru().enqueue(object : Callback<ResponModelEbook> {
            override fun onFailure(call: Call<ResponModelEbook>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponModelEbook>,
                response: Response<ResponModelEbook>
            ) {
                if (response.isSuccessful) {
                    response.body()?.ebook?.let { listImage.addAll(it) }
                    adapterImageSlider = activity?.let { AdapterImageSlider(it, listImage) }!!
                    binding.imageSlider.setSliderAdapter(adapterImageSlider)

                }
            }
        })
    }


    fun getKategori() {
        ApiConfig.instanceRetrofit.getKategori().enqueue(object : Callback<ResponseKategori> {
            override fun onFailure(call: Call<ResponseKategori>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseKategori>,
                response: Response<ResponseKategori>
            ) {
                if (response.isSuccessful && response.body()!!.kategori.isNotEmpty()) {
                    listKategori.addAll(response.body()!!.kategori)

                    adapterListKategori = AdapterListKategori(listKategori, requireActivity())
                    binding.ktEbook.apply {
                        layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = adapterListKategori
                    }
                }
            }
        })
    }

}
