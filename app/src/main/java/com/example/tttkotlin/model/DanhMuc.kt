package com.example.tttkotlin.model

import com.google.gson.annotations.SerializedName

class DanhMuc {
    @SerializedName("id_category")
    private var id_category = 0
    @SerializedName("name")
    private var name="";
    @SerializedName("id_parent")
    private var id_parent="";
    @SerializedName("level")
    private var level=0;

    constructor(id_category: Int, name: String, id_parent: String, level: Int) {
        this.id_category = id_category
        this.name = name
        this.id_parent = id_parent
        this.level = level
    }

    fun setidDanhMuc(id: Int){
        id_category = id
    }
    fun setName(ha: String){
        name = ha
    }
    fun setidParent(td: String){
        id_parent = td
    }
    fun setLevel(sp: Int){
        level = sp
    }
    fun getIdDanhMuc(): Int{
        return id_category
    }
    fun getName(): String{
        return name
    }
    fun getIdParent(): String{
        return id_parent
    }
    fun getLevel(): Int{
        return level
    }
}