package com.example.projek

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.loader.content.CursorLoader
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityDonasiBukuCetakBinding
import com.example.projek.model.ResponModel
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonasiBukuCetak : AppCompatActivity() {
    private lateinit var binding: ActivityDonasiBukuCetakBinding

    var pDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonasiBukuCetakBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCod.setOnClickListener {

        }
        binding.btnPaket.setOnClickListener {
            uploadResi("35",binding.edtNomorResi.text.toString()
            )
        }
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

    private fun showDialog() {
        if (!pDialog?.isShowing!!) pDialog?.show()
    }

    private fun hideDialog() {
        if (pDialog?.isShowing!!) pDialog!!.dismiss()
    }
    private fun uploadResi(
        id_donatur: String,
        buktidonasi: String
    ) {
        pDialog = SweetAlertDialog(this@DonasiBukuCetak, SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog!!.setCancelable(false)
        pDialog!!.titleText = "Mohon Tunggu..."
        showDialog()

        val descBody = RequestBody.create(MediaType.parse("text/plain"), id_donatur)
        val descBody2 = RequestBody.create(MediaType.parse("text/plain"), buktidonasi)

        ApiConfig.instanceRetrofit.donasi_buku(
            descBody,
            descBody2
        ).enqueue(object :
            Callback<ResponModel> {

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                if (response.isSuccessful) {
                    hideDialog()
                    if (response.body()?.message.equals("success")) {
                        Toast.makeText(
                            applicationContext,
                            "Some error occurred...",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "File Uploaded Successfully...",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        })


    }
}
