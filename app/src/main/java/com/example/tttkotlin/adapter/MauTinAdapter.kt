package com.example.tttkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.size.Scale
import com.example.tttkotlin.R
import com.example.tttkotlin.model.MauTin
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MauTinAdapter(var context : Context, var listMauTin:ArrayList<MauTin>): BaseAdapter() {
    override fun getCount(): Int {
        return listMauTin.size
    }

    override fun getItem(position: Int): Any {
        return listMauTin.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewMauTin: View?
        var layoutInflater : LayoutInflater = LayoutInflater.from(context)
        if(position == 0)
            viewMauTin = layoutInflater.inflate(R.layout.item_listview_title, null)
        else {
            viewMauTin = layoutInflater.inflate(R.layout.item_list_view, null)
        }
        val mautin : MauTin = getItem(position) as MauTin

        var imgV : ImageView = viewMauTin.findViewById(R.id.img)
        imgV.load(mautin.getHinhAnh()){
            placeholder(android.R.drawable.ic_menu_gallery)
        }

        var tvT : TextView = viewMauTin.findViewById(R.id.txtTittle)
        tvT.setText(mautin.getTieuDe())

        var tgianTao : String = mautin.getSoPhut().substring(0,18)
        var df : DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var tgianHT :String = df.format(Calendar.getInstance().getTime())
        var format : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        var date1 = format.parse(tgianTao)
        var date2 = format.parse(tgianHT)

        var diff : Long  = 0
        diff = date2.getTime() - date1.getTime()
        var diffSeconds :Long = diff / 1000
        var diffMinutes :Long = diff / (60 * 1000)
        var diffHours :Long = diff / (60 * 60 * 1000)
        var ngay :Long = diff / (60 * 60 * 1000*24)
        var thang :Long = diff / (60 * 60 * 1000*24*30)
        if(thang<0){
            thang=thang*(-1)
        }
        var nam :Long = diff / (60 * 60 * 1000*24*30*12)
        if(nam<0){
            nam=nam*(-1)
        }
        var tvTime : TextView = viewMauTin.findViewById(R.id.txtTime)

        if(diffSeconds<60){
            tvTime.setText(diffSeconds.toString() + " giây trước")
        }
        if(diffSeconds>60&&diffMinutes<60){
            tvTime.setText(diffMinutes.toString() +" phút trước")
        }
        if(diffMinutes>60 && diffHours<24){
            tvTime.setText(diffHours.toString() +" giờ trước")
        }
        if(diffHours>24 && ngay<30){
            tvTime.setText(ngay.toString() +" ngày trước")
        }
        if(ngay>30 && thang<12){
            tvTime.setText(thang.toString() +" tháng trước")
        }
        if(thang>12&&nam<100){
            tvTime.setText(nam.toString() +" năm trước")
        }
        return viewMauTin
    }
}