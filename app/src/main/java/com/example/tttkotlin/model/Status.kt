package com.example.tttkotlin.model

import com.google.gson.annotations.SerializedName

class Status {
    @SerializedName("status")
    private var status:Int = 0
    constructor(st: Int) {
        this.status = st
    }
    fun getStatus(): Int{
        return status
    }
}