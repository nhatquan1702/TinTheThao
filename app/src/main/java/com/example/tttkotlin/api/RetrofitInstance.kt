package com.example.tttkotlin.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.tttkotlin.util.Constants.Companion.BASE_URL

object RetrofitInstance {
    val instance : SimpleApi by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(SimpleApi::class.java)
    }
}