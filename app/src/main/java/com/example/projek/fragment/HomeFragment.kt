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
import com.example.projek.adapter.AdapterListEbook
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.FragmentHomeBinding
import com.example.projek.model.ResponModelEbook
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
    private var listEbook: ArrayList<ResponModelEbook.ModelEbook> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvEbook = view.findViewById(R.id.rv_Ebook)

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
//    val arrayEbook: ArrayList<Ebook>get(){
//        val arr = ArrayList<Ebook>()
//        val p1 = Ebook()
//        p1.gambar = R.drawable.buku1
//
//        val p2 = Ebook()
//        p2.gambar = R.drawable.buku2
//
//        val p3 = Ebook()
//        p3.gambar = R.drawable.buku3
//
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//
//        return arr
//    }


}
