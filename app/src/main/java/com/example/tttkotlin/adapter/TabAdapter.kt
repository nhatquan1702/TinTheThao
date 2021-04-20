package com.example.tttkotlin.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tttkotlin.fragment.BoiLoiFragment
import com.example.tttkotlin.fragment.BongChuyenFragment
import com.example.tttkotlin.fragment.BongDaFragment
import androidx.fragment.app.Fragment as Fragment1
@Suppress("DEPRECATION")
class TabAdapter(var myContext: Context, fm: FragmentManager, internal var totalTabs: Int): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment1 {
        return when (position) {
            0 -> {
                BongDaFragment()
            }
            1 -> {
                BongChuyenFragment()
            }
            2 -> {
                BoiLoiFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}