package com.example.projek

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.ActivityUbahAkunBinding
import com.example.projek.model.ModelUserInfo
import com.example.projek.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbahAkun : AppCompatActivity() {

    private lateinit var binding: ActivityUbahAkunBinding
    var datanya: ModelUserInfo = ModelUserInfo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("data")) {
            datanya = intent.getParcelableExtra("data")!!
        }
        binding.edtNama.setText(datanya.nama)
        binding.edtEmail.setText(datanya.email)
        binding.edtAlamat.setText(datanya.alamat)
        binding.edtNoTelp.setText(datanya.nomor_telepon)

        binding.btnSimpan.setOnClickListener {
            updateProfile(
                "14",
                binding.edtNama.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtAlamat.text.toString(),
                binding.password.text.toString(),
                binding.edtNoTelp.text.toString()
            )
        }

    }

    private fun updateProfile(
        id: String,
        nama: String,
        email: String,
        alamat: String,
        password: String,
        nomortelpon: String
    ) {
        ApiConfig.instanceRetrofit.update_profil(id, nama, email, alamat, password, nomortelpon)
            .enqueue(object :
                Callback<ResponModel> {

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    if (response.isSuccessful && response.body() != null) {
                        if (response.body()!!.message == "success") {
                            Toast.makeText(this@UbahAkun, "Berhasil Ubah", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@UbahAkun, "Gagal Ubah", Toast.LENGTH_LONG).show()

                        }
                    }
                }
            })
    }
}
