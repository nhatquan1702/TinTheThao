package com.example.tttkotlin.adapter

import android.content.Context
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tttkotlin.fragment.BoiLoiFragment
import com.example.tttkotlin.fragment.BongChuyenFragment
import com.example.tttkotlin.fragment.BongDaFragment
@Suppress("DEPRECATION")
class TabAdapter(fm: FragmentActivity, val list:MutableList<Fragment>): FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

}