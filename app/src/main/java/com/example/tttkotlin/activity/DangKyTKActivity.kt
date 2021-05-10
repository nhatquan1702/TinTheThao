package com.example.tttkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.doOnLayout
import com.example.tttkotlin.R
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.Status
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.*
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.checkShowPassDK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DangKyTKActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky_t_k)
        val animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal)
        rLayoutDK.setAnimation(animation)
        val animationx = AnimationUtils.loadAnimation(this,R.anim.tranlation_x)
        tvNhapEmail.setAnimation(animationx)
        edtNhapEmailDK.setAnimation(animationx)
        tvNhapUserName.setAnimation(animationx)
        edtNhapUsernameDK.setAnimation(animationx)
        tvNhapPass1.setAnimation(animationx)
        edtNhapPass1.setAnimation(animationx)
        tvNhapPass2.setAnimation(animationx)
        edtNhapPass2.setAnimation(animationx)
        checkShowPassDK.setAnimation(animationx)
        checkShowPassDK.setOnClickListener{
            if(checkShowPassDK.isChecked)    {
                edtNhapPass1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                edtNhapPass2.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                edtNhapPass1.transformationMethod = PasswordTransformationMethod.getInstance()
                edtNhapPass2.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        cvDKTK.setOnClickListener {
            val username = edtNhapUsernameDK.text.toString().trim()
            val email = edtNhapEmailDK.text.toString().trim()
            val pass1 = edtNhapPass1.text.toString().trim()
            val pass2 = edtNhapPass2.text.toString().trim()

            if(username.isEmpty()){
                edtNhapUsernameDK.error = "Vui lòng kiểm tra lại tên người dùng!"
                edtNhapUsernameDK.requestFocus()
                return@setOnClickListener
            }
            if(email.isEmpty()){
                edtNhapEmailDK.error = "Vui lòng kiểm tra lại email!"
                edtNhapEmailDK.requestFocus()
                return@setOnClickListener
            }
            if(pass1.isEmpty()){
                edtNhapPass1.error = "Vui lòng kiểm tra lại mật khẩu!"
                edtNhapPass1.requestFocus()
                return@setOnClickListener
            }
            if(pass2.isEmpty()){
                edtNhapPass2.error = "Vui lòng kiểm tra lại mật khẩu!"
                edtNhapPass2.requestFocus()
                return@setOnClickListener
            }
            if(!pass1.equals(pass2) || !pass2.equals(pass1))
                edtNhapPass2.error="Mật khẩu chưa trùng khớp!"
            dangKyTaiKhoan(username, email, pass2)
        }

    }
    fun dangKyTaiKhoan(username:String, email:String, pass:String){
        RetrofitInstance.instance.dangKyTaiKhoan(username, email, pass).enqueue(object : Callback<Status> {
            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                response.body()?.let{
                    if(it.getStatus()==0){
                        Toast.makeText(applicationContext,"Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        onBackPressed()
                    }
                    if(it.getStatus()==1){
                        edtNhapUsernameDK.error="Vui lòng kiểm tra lại tên người dùng!"
                    }
                    if(it.getStatus()==2){
                        edtNhapUsernameDK.error="Vui lòng kiểm tra lại email!"
                    }
                    if(it.getStatus()==3){
                        edtNhapPass1.error="Vui lòng kiểm tra lại mật khẩu!"
                        edtNhapPass2.error="Vui lòng kiểm tra lại mật khẩu!"
                    }
                    if(it.getStatus()==4){
                        edtNhapEmailDK.error="Email đã tồn tại!"
                    }
                }
            }
            override fun onFailure(call: Call<Status>, t: Throwable) {
                Toast.makeText(applicationContext,"Lỗi!"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}