package com.example.projek.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

import com.example.projek.R
import com.example.projek.adapter.AdapterEbook
import com.example.projek.adapter.AdapterSlider
import com.example.projek.model.Ebook


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvEbook:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvEbook = view.findViewById(R.id.rv_Ebook)


        val arraySlider= ArrayList<Int>()
        arraySlider.add(R.drawable.buku1)
        arraySlider.add(R.drawable.buku2)
        arraySlider.add(R.drawable.buku3)

        val adapterSlider = AdapterSlider(arraySlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvEbook.adapter= AdapterEbook(arrayEbook)
        rvEbook.layoutManager = layoutManager


        return view
    }

    val arrayEbook: ArrayList<Ebook>get(){
        val arr = ArrayList<Ebook>()
        val p1 = Ebook()
        p1.gambar = R.drawable.buku1

        val p2 = Ebook()
        p2.gambar = R.drawable.buku2

        val p3 = Ebook()
        p3.gambar = R.drawable.buku3


        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }


}
