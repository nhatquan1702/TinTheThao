package com.example.tttkotlin.model

import com.google.gson.annotations.SerializedName

class Login {
    @SerializedName("access_token")
    private var access_token="";
    @SerializedName("refresh_token")
    private var refresh_token="";
    fun getAccessToken(): String{
        return access_token
    }
    fun getRefreshToken(): String{
        return refresh_token
    }
}