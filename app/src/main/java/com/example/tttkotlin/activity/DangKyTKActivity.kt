package com.example.tttkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.doOnLayout
import com.example.tttkotlin.R
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.*

class DangKyTKActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky_t_k)
//        rLayoutDK?.apply {
//            doOnLayout {
//                val animation = AnimationUtils.loadAnimation(this@DangKyTKActivity, R.anim.uptodowndiagonal)
//                startAnimation(animation)
//                animation?.setAnimationListener(this@DangKyTKActivity)
//            }
//        }
        val animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rLayoutDK.setAnimation(animation);
    }

//    override fun onAnimationStart(animation: Animation?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onAnimationEnd(animation: Animation?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onAnimationRepeat(animation: Animation?) {
//        TODO("Not yet implemented")
//    }
}