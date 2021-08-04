package com.example.projek

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityPdfBinding
import com.example.projek.model.ResponModelEbook
import java.io.File

class Pdf : AppCompatActivity() {
    lateinit var binding: ActivityPdfBinding
    lateinit var pDialog: SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PRDownloader.initialize(applicationContext)
        if (intent.hasExtra("pdf")) {
            val detailEbook: ResponModelEbook.ModelEbook? = intent.getParcelableExtra("pdf")
            if (detailEbook != null) {
                try {
                    val fileName = "myFile.pdf"
                    downloadPdfFromInternet(
                        ApiConfig.BASE_URL_FILE + detailEbook.file_ebook,
                        getRootDirPath(this),
                        fileName
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    private fun downloadPdfFromInternet(url: String, dirPath: String, fileName: String) {
        pDialog = SweetAlertDialog(this@Pdf, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#DA1F3E")
        pDialog.setCancelable(false)
        pDialog.titleText = "Mohon Tunggu..."
        showDialog()
        PRDownloader.download(
            url,
            dirPath,
            fileName
        ).build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    hideDialog()
                    Toast.makeText(this@Pdf, "downloadComplete", Toast.LENGTH_LONG)
                        .show()
                    val downloadedFile = File(dirPath, fileName)
//                    progressBar.visibility = View.GONE
                    showPdfFromFile(downloadedFile)
                }

                override fun onError(error: com.downloader.Error?) {
                    hideDialog()
                    Toast.makeText(
                        this@Pdf,
                        "Error in downloading file : $error",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }


            })
    }

    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }

    private fun showPdfFromFile(file: File) {
        binding.pdfView.visibility = View.VISIBLE
        binding.pdfView.fromFile(file)
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError { page, _ ->
                Toast.makeText(
                    this@Pdf,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

    fun getRootDirPath(context: Context): String {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file: File = ContextCompat.getExternalFilesDirs(
                context.applicationContext,
                null
            )[0]
            file.absolutePath
        } else {
            context.applicationContext.filesDir.absolutePath
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}