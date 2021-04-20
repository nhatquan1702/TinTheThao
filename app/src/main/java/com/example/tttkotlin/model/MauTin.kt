package com.example.tttkotlin.model

import com.google.gson.annotations.SerializedName

public class MauTin {
    @SerializedName("id_post")
    private var idMauTin=0;
    @SerializedName("img")
    private var hinhAnh="";
    @SerializedName("title")
    private var tieuDe="";
    @SerializedName("create_time")
    private var soPhut="";
    @SerializedName("content")
    private var noidung="";
    @SerializedName("rating")
    private var danhgia =0.0;
    @SerializedName("username")
    private var nguoiTao="";

    constructor(idMauTin: Int, hinhAnh: String, tieuDe: String, soPhut: String) {
        this.idMauTin = idMauTin
        this.hinhAnh = hinhAnh
        this.tieuDe = tieuDe
        this.soPhut = soPhut
    }
    constructor(idMauTin: Int, hinhAnh: String, tieuDe: String, soPhut: String, noiDung:String, danhGia:Float, nguoiTao : String) {
        this.idMauTin = idMauTin
        this.hinhAnh = hinhAnh
        this.tieuDe = tieuDe
        this.soPhut = soPhut
        this.noidung = noiDung
        this.danhgia = danhGia.toDouble()
        this.nguoiTao = nguoiTao
    }

    fun setidMauTin(id: Int){
        idMauTin = id
    }
    fun setHinhAnh(ha: String){
        hinhAnh = ha
    }
    fun setTieuDe(td: String){
        tieuDe = td
    }
    fun setSoPhut(sp: String){
        soPhut = sp
    }
    fun getIdMauTin(): Int{
        return idMauTin
    }
    fun getHinhAnh(): String{
        return hinhAnh
    }
    fun getTieuDe(): String{
        return tieuDe
    }
    fun getNoiDung(): String{
        return noidung
    }
    fun getDanhGia(): Double{
        return danhgia
    }
    fun getNguoiTao(): String{
        return nguoiTao
    }
    fun getSoPhut(): String{
        return soPhut
    }
}