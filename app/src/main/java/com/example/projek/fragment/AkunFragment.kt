package com.example.projek.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projek.R
import com.example.projek.UbahAkun
import com.example.projek.app.ApiConfig
import com.example.projek.databinding.FragmentAkunBinding
import com.example.projek.model.ModelUserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class AkunFragment : Fragment() {
    private lateinit var binding: FragmentAkunBinding
    var data: ModelUserInfo? = ModelUserInfo()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAkunBinding.bind(view)
        akun("14")
        binding.btnUbah.setOnClickListener {

            val i = Intent(activity, UbahAkun::class.java)
            i.putExtra("data", data)
            startActivityForResult(i, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            akun("14")
        }
    }

    private fun akun(id_user: String) {
        ApiConfig.instanceRetrofit.userInfo(id_user).enqueue(object : Callback<ModelUserInfo> {
            override fun onFailure(call: Call<ModelUserInfo>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ModelUserInfo>,
                response: Response<ModelUserInfo>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    data = response.body()
                    binding.namaUser.text = response.body()!!.nama
                    binding.emailUser.text = response.body()!!.email
                    binding.alamatUser.text = response.body()!!.alamat
                    binding.notelpUser.text = response.body()!!.nomor_telepon

                }

            }
        }

        )
    }
}
