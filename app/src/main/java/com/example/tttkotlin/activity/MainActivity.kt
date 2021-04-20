package com.example.tttkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tttkotlin.R
import com.example.tttkotlin.fragment.HomeFragment
import kotlinx.android.synthetic.main.nav_header.*


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment())

    }
    fun AppCompatActivity.replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
        .replace(R.id.main_layout, fragment)
        .addToBackStack(null)
        .commit()
    }
}