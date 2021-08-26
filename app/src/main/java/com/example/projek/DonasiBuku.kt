package com.example.projek

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projek.databinding.ActivityDonasiBukuBinding

class DonasiBuku : Fragment() {
    private lateinit var binding:ActivityDonasiBukuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityDonasiBukuBinding.bind(view)

        binding.btnPengajuanDonasi.setOnClickListener {
            startActivity(Intent(requireActivity(),PengajuanDonasi::class.java))
        }

        binding.btnDonasiBukuCetak.setOnClickListener {
            startActivity(Intent(requireActivity(),DonasiBukuCetak::class.java))
        }

        binding.btnDonasiEbook.setOnClickListener {
            startActivity(Intent(requireActivity(),DonasiEbook::class.java))
        }

        binding.btnKetentuanDonasi.setOnClickListener {
            startActivity(Intent(requireActivity(),KetentuanDonasi::class.java))
        }

        binding.btnInfoDonasi.setOnClickListener {
            startActivity(Intent(requireActivity(),InfoDonasi::class.java))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_donasi_buku, container, false)


    }


}