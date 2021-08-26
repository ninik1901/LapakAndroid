package com.example.projek

import android.Manifest
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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
import java.io.File

class DonasiBukuCetak : AppCompatActivity() {
    private lateinit var binding: ActivityDonasiBukuCetakBinding
    var selectedImage: Uri? = null

    var pDialog: SweetAlertDialog? = null
    var idnya: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonasiBukuCetakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idnya = intent.getStringExtra("id").toString()
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
        binding.pilihFile.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 100)
        }
        binding.btnCod.setOnClickListener {
            selectedImage?.let {

                uploadFile(
                    idnya,
                    selectedImage!!
                )


            }
        }
        binding.btnPaket.setOnClickListener {

                uploadResi(
                    idnya, binding.edtNomorResi.text.toString()
                )

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
        id_donatur: String, fileUri: Uri
    ) {
        pDialog = SweetAlertDialog(this@DonasiBukuCetak, SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog!!.setCancelable(false)
        pDialog!!.titleText = "Mohon Tunggu..."
        showDialog()
        //creating a file
        val file = File(getRealPathFromURI(fileUri))

        //creating request body for file
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse(contentResolver.getType(fileUri)), file)
        val descBody = RequestBody.create(MediaType.parse("text/plain"), id_donatur)


        //creating our api

        //creating a call and calling the upload image method
        ApiConfig.instanceRetrofit.donasi_cod(
            descBody,
            requestFile

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
                t.printStackTrace()
            }
        })

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

        ApiConfig.instanceRetrofit.donasi_paket(
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
