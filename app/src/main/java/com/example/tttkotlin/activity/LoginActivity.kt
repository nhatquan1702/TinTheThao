package com.example.tttkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
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
            RetrofitInstance.instance.userLogin(email, pass).enqueue(object : Callback<Login>{
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if(response.body()?.getAccessToken().equals("")){
                        Toast.makeText(applicationContext, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(applicationContext,"Đăng nhập thành công!" , Toast.LENGTH_SHORT).show()
//                        val intent = Intent(applicationContext, MainActivity::class.java)
//                        intent.putExtra("access_token", response.body()?.getAccessToken())
//                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Toast.makeText(applicationContext, "Vui lòng kiểm tra lại tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}