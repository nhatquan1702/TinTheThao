package com.example.tttkotlin.activity

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase.create
import android.os.Build
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.AnimationUtils
import java.util.Objects;
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair.create
import com.example.tttkotlin.R
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.Login
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.checkShowPass
import kotlinx.android.synthetic.main.activity_login.cvDNLogin
import kotlinx.android.synthetic.main.activity_login.edtEmailLogin
import kotlinx.android.synthetic.main.activity_login.edtPassLogin
import kotlinx.android.synthetic.main.activity_login_new.*
import kotlinx.android.synthetic.main.nav_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI.create


class LoginActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
        val animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal)
        rLayoutDN.setAnimation(animation)
        val animationx = AnimationUtils.loadAnimation(this,R.anim.tranlation_x)
        edtEmailLogin.setAnimation(animationx)
        tvEmailDN.setAnimation(animationx)
        edtPassLogin.setAnimation(animationx)
        tvPassDN.setAnimation(animationx)
        checkShowPass.setAnimation(animationx)
        val animationy = AnimationUtils.loadAnimation(this,R.anim.tranlation_y)
        rLayoutAdd.setAnimation(animationy)
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
                    val sharedPreferences: SharedPreferences = getSharedPreferences(
                        "dataLogin",
                        Context.MODE_PRIVATE
                    )
                    var editor = sharedPreferences.edit()
                    if (!response.body()?.getAccessToken().equals("")) {
                        editor.apply {
                            putString("email", email)
                            putString("token", response.body()?.getAccessToken())
                            putString("pass", pass)
                        }.apply()
                        editor.commit()
                        Toast.makeText(
                            applicationContext,
                            "Đăng nhập thành công!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        //intent.putExtra("email_user", email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Đăng nhập thất bại!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Vui lòng kiểm tra lại tài khoản hoặc mật khẩu!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }

        imgbtnDKTK.setOnClickListener {
            val intent = Intent(applicationContext, DangKyTKActivity::class.java)
            startActivity(intent)
        }
//        tvDKTKNew.setOnClickListener {
//            val intent = Intent(applicationContext, DangKyTKActivity::class.java)
//            startActivity(intent)
//        }

    }

}