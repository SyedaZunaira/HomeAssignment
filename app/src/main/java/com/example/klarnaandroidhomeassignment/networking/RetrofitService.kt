package com.example.klarnaandroidhomeassignment.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    var BASE_URL: String = "https://api.darksky.net/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> cteateService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}