package com.example.projek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projek.app.ApiConfig
import com.example.projek.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edt_email
import kotlinx.android.synthetic.main.activity_login.edt_password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_masuk.setOnClickListener {
            login()
        }

        tv_daftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        tv_reset.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ResetPassword::class.java))
        }
    }

    fun login() {
        if (edt_email.text.isEmpty()) {
            edt_email.error = "Kolom email tidak boleh kosong"
            edt_email.requestFocus()
            return
        } else if (edt_password.text.isEmpty()) {
            edt_password.error = "Kolom password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }
        btn_masuk.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        ApiConfig.instanceRetrofit.login(edt_email.text.toString(),edt_password.text.toString()).enqueue(object: Callback<ResponModel> {

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                val respon = response.body()!!

                if (respon.success == 1){
                    Toast.makeText(this@LoginActivity, "Success:"+respon.message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@LoginActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }


        })
    }
}
