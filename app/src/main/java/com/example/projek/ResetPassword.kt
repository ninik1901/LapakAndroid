package com.example.projek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projek.app.ApiConfig
import com.example.projek.model.ResponModel
import kotlinx.android.synthetic.main.activity_reset_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        btn_reset.setOnClickListener {
            reset()

        }
        tv_login.setOnClickListener {
            startActivity(Intent(this@ResetPassword, LoginActivity::class.java))
        }
    }

    fun reset() {
        if(edt_email.text.isEmpty()) {
            edt_email.error="Kolom nama tidak boleh kosong"
            edt_email.requestFocus()
            return
        }else  if(edt_noTelp.text.isEmpty()){
            edt_noTelp.error="Kolom email tidak boleh kosong"
            edt_noTelp.requestFocus()
            return
        }else  if(edt_password.text.isEmpty()){
            edt_password.error="Kolom password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.reset(edt_email.text.toString(),edt_noTelp.text.toString(),edt_password.text.toString()).enqueue(object:Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@ResetPassword, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!

                if (respon.success!!) {
                    Toast.makeText(
                        this@ResetPassword,
                        "Success:" + respon.message,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@ResetPassword,
                        "Eror:" + respon.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })

        }
}
