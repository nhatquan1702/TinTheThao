package com.example.tttkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.doOnLayout
import com.example.tttkotlin.R
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.*
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.checkShowPass
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtEmailLogin
import kotlinx.android.synthetic.main.activity_login.edtPassLogin
import kotlinx.android.synthetic.main.activity_login_new.*

class DangKyTKActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky_t_k)
        val animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal)
        rLayoutDK.setAnimation(animation)
        val animationx = AnimationUtils.loadAnimation(this,R.anim.tranlation_x)
        tvNhapEmail.setAnimation(animationx)
        edtNhapEmail.setAnimation(animationx)
        tvNhapPass1.setAnimation(animationx)
        edtNhapPass1.setAnimation(animationx)
        tvNhapPass2.setAnimation(animationx)
        edtNhapPass2.setAnimation(animationx)
        checkShowPass.setAnimation(animationx)
    }
}