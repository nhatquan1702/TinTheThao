package com.example.tttkotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tttkotlin.R
import com.example.tttkotlin.SearchActivity
import com.example.tttkotlin.adapter.MauTinAdapter
import com.example.tttkotlin.adapter.TabAdapter
import com.example.tttkotlin.fragment.BoiLoiFragment
import com.example.tttkotlin.fragment.BongChuyenFragment
import com.example.tttkotlin.fragment.BongDaFragment
import com.example.tttkotlin.model.DanhMuc
import com.example.tttkotlin.model.MauTin
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_login_new.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(){
    lateinit var listDanhMuc : ArrayList<DanhMuc>
    lateinit var tabLayout : TabLayout
    lateinit var listMauTin:ArrayList<MauTin>
    lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //replaceFragment(HomeFragment())
        tabLayout = findViewById(R.id.tabLayout)
        listDanhMuc = arrayListOf()
        loadTabLayout()
        val sharedPreferences: SharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE)
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val navUsername:TextView = headerView.findViewById<View>(R.id.tvHoTenNa_Header) as TextView
        val btnLoginHeader:Button = headerView.findViewById<View>(R.id.btnHeaderLogin) as Button
        val imgAVTHeader:ImageView = headerView.findViewById<View>(R.id.imgAVT) as ImageView
        var check = sharedPreferences.getString("token", "-1")
        if(!check.equals("-1")){//đã đăng nhập
            //onBackPressed()
            navUsername.setText(sharedPreferences.getString("username", "username"))
            btnLoginHeader.setText("Đăng xuất")
            imgAVTHeader.setImageResource(R.drawable.deappool)
            btnLoginHeader.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                var editor = sharedPreferences.edit()
                editor.apply {
                    editor.remove("email")
                    editor.remove("pass")
                    editor.remove("token")
                    editor.remove("username")
                }.apply()
                editor.commit()
                Toast.makeText(applicationContext, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show()
            }
        }
         if(check.equals("-1")){//chưa đăng nhập
             navUsername.setText("username");
             btnLoginHeader.setText("Đăng nhập")
             imgAVTHeader.setImageResource(R.drawable.ic_launcher_foreground)
            btnLoginHeader.setOnClickListener {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        search()
        iconToolbar.setOnClickListener(View.OnClickListener {
            drawerlayout.toggleMenu()
        })
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.H -> {
                    Toast.makeText(applicationContext, "Home!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.CS -> {
                    Toast.makeText(applicationContext, "Chia sẻ!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.GY -> {
                    Toast.makeText(applicationContext, "Góp ý!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.CD -> {
                    Toast.makeText(applicationContext, "Cài đặt!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.HT -> {
                    Toast.makeText(applicationContext, "Hỗ trợ!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.DGUD -> {
                    Toast.makeText(applicationContext, "Đánh giá ứng dụng!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.DKSD -> {
                    Toast.makeText(applicationContext, "Điều khoản sử dụng!", Toast.LENGTH_SHORT).show()
                    true

                }
                else -> false
            }
        }

    }
    fun search(){
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra("textSearch", query)
                startActivity(intent)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
    }
    fun loadTabLayout(){
        val tabadapter = TabAdapter(this@MainActivity, mutableListOf(BongChuyenFragment(), BoiLoiFragment(), BongDaFragment()))
        viewPager.adapter = tabadapter
        TabLayoutMediator(tabLayout,viewPager,{ tab,pos->
            when(pos){
                0-> tab.text = "Bóng đá"
                1->tab.text="Bơi lội"
                2->tab.text="Bóng bàn"
            }
        }).attach()

    }

}