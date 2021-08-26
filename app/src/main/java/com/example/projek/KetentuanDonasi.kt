package com.example.projek

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.projek.databinding.ActivityKetentuanDonasiBinding

class KetentuanDonasi : AppCompatActivity() {
    private lateinit var binding: ActivityKetentuanDonasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKetentuanDonasiBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_ketentuan_donasi)

        back()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun back() {
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
//        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
