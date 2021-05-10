package com.example.tttkotlin.api

import com.example.tttkotlin.model.DanhMuc
import com.example.tttkotlin.model.Login
import com.example.tttkotlin.model.MauTin
import com.example.tttkotlin.model.Status
import retrofit2.Call
import retrofit2.http.*

interface SimpleApi {
    @GET("post?state=id_category=1,status=1&fields=id_post,title,img,create_time&limit=10&sort=create_time,desc")
    fun getListMauTin1() : Call<ArrayList<MauTin>>

    @GET("post?state=id_category=2,status=1&fields=id_post,title,img,create_time&limit=10&sort=create_time,desc")
    fun getListMauTin2() : Call<ArrayList<MauTin>>

    @GET("post?state=id_category=3,status=1&fields=id_post,title,img,create_time&limit=10&sort=create_time,desc")
    fun getListMauTin3() : Call<ArrayList<MauTin>>

    @GET("category")
    fun getListNameDanhMuc() : Call<ArrayList<DanhMuc>>

    @GET("post/{id}")
    fun getMauTin(@Path("id") id :String) : Call<ArrayList<MauTin>>

    @GET("login")
    fun userLogin(@Header ("email") user : String, @Header("password") pass : String) : Call<Login>

    @POST("post/view/{id}")
    fun addView(@Path("id") id :String) : Call<Status>

    @POST("account/reg")
    fun dangKyTaiKhoan(@Header ("username") username : String, @Header ("email") email : String, @Header("password") pass : String) : Call<Status>
}