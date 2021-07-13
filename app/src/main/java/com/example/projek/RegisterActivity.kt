package com.example.projek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projek.app.ApiConfig
import com.example.projek.model.ResponModel
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_daftar.setOnClickListener {
            register()

        }

        tv_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    fun login(){

    }

    fun register(){
        if(edt_nama.text.isEmpty()) {
            edt_nama.error="Kolom nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        }else  if(edt_email.text.isEmpty()){
            edt_email.error="Kolom email tidak boleh kosong"
            edt_email.requestFocus()
            return
        }else  if(edt_password.text.isEmpty()){
            edt_password.error="Kolom password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }else  if(edt_alamat.text.isEmpty()){
            edt_alamat.error="Kolom alamat tidak boleh kosong"
            edt_alamat.requestFocus()
            return
        }else if(edt_telepon.text.isEmpty()){
            edt_telepon.error="Kolom telepon tidak boleh kosong"
            edt_telepon.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(),edt_email.text.toString(),edt_password.text.toString(),edt_alamat.text.toString(),edt_telepon.text.toString()).enqueue(object:Callback<ResponModel>{

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                val respon = response.body()!!

                if (respon.success == 1){
                    Toast.makeText(this@RegisterActivity, "Success:"+respon.message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@RegisterActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }


        })
    }
}
