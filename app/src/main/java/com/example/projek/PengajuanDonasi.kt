package com.example.projek

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.loader.content.CursorLoader
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityPengajuanDonasiBinding
import com.example.projek.model.ResponModel
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class PengajuanDonasi : AppCompatActivity() {
    var selectedImage: Uri? = null
    private lateinit var binding: ActivityPengajuanDonasiBinding
    var pilihan =
        arrayOf("Pilih Kategori", "Ebook", "Buku")
    var kirim =
        arrayOf("Pilih Pengiriman", "COD", "Paket")
    var paket: String = ""
    var buku: String = ""
    var pDialog: SweetAlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanDonasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:$packageName")
            )
            finish()
            startActivity(intent)
            return
        }


        val spinnerArrayAdapter: ArrayAdapter<Any?> = ArrayAdapter(
            this@PengajuanDonasi,
            R.layout.simple_list_item_1,
            pilihan
        )
        val pengiriman: ArrayAdapter<Any?> = ArrayAdapter(
            this@PengajuanDonasi,
            R.layout.simple_list_item_1,
            kirim
        )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view
        pengiriman.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view

        binding.sp1.adapter = spinnerArrayAdapter
        binding.sp2.adapter = pengiriman

        binding.sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                if (i > 0) {
                    if (i == 2) {
                        buku = pilihan[i]
                        binding.layoutFile.visibility = View.VISIBLE

                    } else if (i == 1) {
                        buku = pilihan[i]
                        binding.layoutFile.visibility = View.GONE

                    }
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        binding.sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                if (i > 0) {

                    paket = kirim[i]
                    Toast.makeText(
                        applicationContext,
                        paket,
                        Toast.LENGTH_LONG
                    ).show()

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }


        binding.btnKirimPengajuan.setOnClickListener {

            if (buku == "Buku") {
                selectedImage?.let {
                    uploadFile(
                        binding.edtJudulBuku.text.toString(),
                        buku,
                        binding.edtAlamatDonatur.text.toString(),
                        "14",
                        binding.edtSinopsis.text.toString(),
                        paket,
                        binding.edtJmlDonasi.text.toString(),
                        it
                    )
                }
            } else {
                uploadEbook(
                    binding.edtJudulBuku.text.toString(),
                    buku,
                    binding.edtAlamatDonatur.text.toString(),
                    "14",
                    binding.edtSinopsis.text.toString(),
                    paket,
                    binding.edtJmlDonasi.text.toString()
                )

            }

        }
        binding.pilihFile.setOnClickListener { v ->
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 100)
        }
        back()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            //the image URI
            selectedImage = data?.data
            if (selectedImage != null) {
                Toast.makeText(
                    applicationContext,
                    getRealPathFromURI(selectedImage!!),
                    Toast.LENGTH_LONG
                ).show()

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
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

    private fun getRealPathFromURI(contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(this, contentUri, proj, null, null, null)
        val cursor: Cursor = loader.loadInBackground()!!
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result: String = cursor.getString(column_index)
        cursor.close()
        return result
    }

    private fun uploadFile(
        judulbuku: String,
        jenis_buku: String,
        alamat_donatur: String,
        donatur: String,
        sinopsis: String,
        jenis_donasi: String,
        jumlah_buku: String, fileUri: Uri
    ) {
        pDialog = SweetAlertDialog(this@PengajuanDonasi, SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog!!.setCancelable(false)
        pDialog!!.titleText = "Mohon Tunggu..."
        showDialog()
        //creating a file
        val file = File(getRealPathFromURI(fileUri))

        //creating request body for file
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse(contentResolver.getType(fileUri)), file)
        val descBody = RequestBody.create(MediaType.parse("text/plain"), judulbuku)
        val descBody2 = RequestBody.create(MediaType.parse("text/plain"), jenis_buku)
        val descBody4 = RequestBody.create(MediaType.parse("text/plain"), alamat_donatur)
        val descBody5 = RequestBody.create(MediaType.parse("text/plain"), donatur)
        val descBody7 = RequestBody.create(MediaType.parse("text/plain"), sinopsis)
        val descBody3 = RequestBody.create(MediaType.parse("text/plain"), jenis_donasi)
        val descBody6 = RequestBody.create(MediaType.parse("text/plain"), jumlah_buku)


        //creating our api

        //creating a call and calling the upload image method
        ApiConfig.instanceRetrofit.pengajuan_buku(
            requestFile,
            descBody,
            descBody2,
            descBody4,
            descBody5,
            descBody7,
            descBody3,
            descBody6
        ).enqueue(object : Callback<ResponModel> {
            override fun onResponse(
                call: Call<ResponModel>,
                response: Response<ResponModel>
            ) {
                if (response.isSuccessful) {
                    hideDialog()
                    if (response.body()?.message.equals("success")) {
                        Toast.makeText(
                            applicationContext,
                            "File Uploaded Successfully...",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Some error occurred...",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun uploadEbook(
        judulbuku: String,
        jenis_buku: String,
        alamat_donatur: String,
        donatur: String,
        sinopsis: String,
        jenis_donasi: String,
        jumlah_buku: String
    ) {
        pDialog = SweetAlertDialog(this@PengajuanDonasi, SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog!!.setCancelable(false)
        pDialog!!.titleText = "Mohon Tunggu..."
        showDialog()
        ApiConfig.instanceRetrofit.pengajuan_ebook(
            judulbuku,
            jenis_buku,
            alamat_donatur,
            donatur,
            sinopsis,
            jenis_donasi,
            jumlah_buku
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
                            "File Uploaded Successfully...",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Some error occurred...",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        })

    }
}
