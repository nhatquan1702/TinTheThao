package com.example.tttkotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tttkotlin.R
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.Login
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.nav_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkShowPass.setOnClickListener{
            if(checkShowPass.isChecked)    {
                edtPassLogin.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                edtPassLogin.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        cvDNLogin.setOnClickListener {
            val email = edtEmailLogin.text.toString().trim()
            val pass = edtPassLogin.text.toString().trim()

            if(email.isEmpty()){
                edtEmailLogin.error = "Vui lòng kiểm tra lại tài khoản!"
                edtEmailLogin.requestFocus()
                return@setOnClickListener
            }
            if(pass.isEmpty()){
                edtPassLogin.error = "Vui lòng kiểm tra lại mật khẩu!"
                edtPassLogin.requestFocus()
                return@setOnClickListener
            }


            RetrofitInstance.instance.userLogin(email, pass).enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    val sharedPreferences: SharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE)
                    var editor = sharedPreferences.edit()
                    if (!response.body()?.getAccessToken().equals("")) {
                            editor.apply {
                                putString("email", email)
                                putString("token", response.body()?.getAccessToken())
                                putString("pass", pass)
                            }.apply()
                            editor.commit()
                            Toast.makeText(applicationContext, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        //intent.putExtra("email_user", email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Toast.makeText(applicationContext, "Vui lòng kiểm tra lại tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}