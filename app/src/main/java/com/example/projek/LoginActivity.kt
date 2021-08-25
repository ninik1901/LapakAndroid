package com.example.projek

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projek.app.ApiConfig
import com.example.projek.app.SessionManager
import com.example.projek.model.ResponModel
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    var token: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task: Task<String?> ->
                if (!task.isSuccessful) {
                    Log.w("gagal", "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                // Get new FCM registration token
                token = task.result.toString()
                assert(token != null)
                Log.d("token", token!!)
            }
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
            ApiConfig.instanceRetrofit.login(
                edt_email.text.toString(),
                edt_password.text.toString(),
                token.toString()
            ).enqueue(object : Callback<ResponModel> {

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error:" + t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                    val respon = response.body()!!

                    if (response.body()!!.success!!) {
                        Toast.makeText(
                            this@LoginActivity,
                            respon.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        SessionManager.setLogin(applicationContext, true)
                        SessionManager.setidUser(
                            applicationContext,
                            response.body()!!.user!!.id.toString()
                        )
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            response.body()!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            })
        }


    }
}
