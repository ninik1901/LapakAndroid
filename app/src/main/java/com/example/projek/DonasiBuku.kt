package com.example.projek

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_donasi_buku.*
import kotlinx.android.synthetic.main.activity_login.*

class DonasiBuku : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_donasi_buku, container, false)

        btn_pengajuan_donasi.setOnClickListener {
            startActivity(Intent(requireActivity(), PengajuanDonasi::class.java))
        }
    }


}