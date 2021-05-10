package com.example.tttkotlin.model

import com.google.gson.annotations.SerializedName

class Login {
    @SerializedName("access_token")
    private var access_token="";
    @SerializedName("refresh_token")
    private var refresh_token="";
    @SerializedName("email")
    private var email="";
    @SerializedName("username")
    private var username="";
    fun getAccessToken(): String{
        return access_token
    }
    fun getRefreshToken(): String{
        return refresh_token
    }
    fun getUserName(): String{
        return username
    }
    fun getEmail(): String{
        return email
    }
}