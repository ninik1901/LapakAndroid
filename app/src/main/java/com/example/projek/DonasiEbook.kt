package com.example.projek

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityDonasiEbookBinding
import com.example.projek.model.ResponModel
import com.nbsp.materialfilepicker.MaterialFilePicker
import com.nbsp.materialfilepicker.ui.FilePickerActivity
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.regex.Pattern

class DonasiEbook : AppCompatActivity() {
    private lateinit var binding: ActivityDonasiEbookBinding
//    var selectedImage: Uri? = null
    var pdfPath: String = ""
    var idnya: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonasiEbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idnya = intent.getStringExtra("id").toString()
        Log.d("as", idnya)
        binding.pilihFile.setOnClickListener {
            //            val intent = Intent()
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type = "application/pdf"
//            startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 1)

            launchPicker()
        }
        binding.btnDonasiEbook.setOnClickListener {
            uploadFile(idnya, binding.edtJudulEbook.text.toString(), pdfPath)

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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("datanya", data.toString())

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //the image URI
//            selectedImage = data!!.data

            val path = data?.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)
            if (path != null) {
                Log.d("Path: ", path)
                pdfPath = path
                Toast.makeText(this@DonasiEbook, "Picked file: $path", Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadFile(
        id_donatur: String,
        judul_buku: String,
        filePath: String
    ) {
        pDialog = SweetAlertDialog(this@DonasiEbook, SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog!!.setCancelable(false)
        pDialog!!.titleText = "Mohon Tunggu..."
        showDialog()
        //creating a file
        val file = File(filePath)

        //creating request body for file
        val requestFile: RequestBody =
            RequestBody.create(
                MediaType.parse("application/pdf"),
                file
            )
        val descBody = RequestBody.create(MediaType.parse("text/plain"), id_donatur)
        val descBody2 = RequestBody.create(MediaType.parse("text/plain"), judul_buku)


        //creating our api

        //creating a call and calling the upload image method
        ApiConfig.instanceRetrofit.donasi_ebook(
            requestFile,
            descBody,
            descBody2
        ).enqueue(object : Callback<ResponModel> {
            override fun onResponse(
                call: Call<ResponModel>,
                response: Response<ResponModel>
            ) {
                if (response.isSuccessful) {
                    hideDialog()
                    if (response.body()?.message.equals("success")) {
                        Toast.makeText(
                            this@DonasiEbook,
                            "File Uploaded Successfully...",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@DonasiEbook,
                            "Some error occurred...",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@DonasiEbook, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    var pDialog: SweetAlertDialog? = null
    private fun showDialog() {
        if (!pDialog?.isShowing!!) pDialog?.show()
    }

    private fun hideDialog() {
        if (pDialog?.isShowing!!) pDialog!!.dismiss()
    }

    //    private fun getFileName(uri: Uri): String? {
//        var result: String? = null
//        if (uri.scheme == "content") {
//            val cursor: Cursor? =
//                requireActivity().contentResolver.query(uri, null, null, null, null)
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                }
//            } finally {
//                cursor?.close()
//            }
//        }
//        if (result == null) {
//            result = uri.lastPathSegment
//        }
//        return result
//    }
    private fun launchPicker() {
        MaterialFilePicker()
            .withActivity(this@DonasiEbook)
            .withRequestCode(1)
            .withHiddenFiles(true)
            .withFilter(Pattern.compile(".*\\.pdf$"))
            .withTitle("Select PDF file")
            .start()
    }

}