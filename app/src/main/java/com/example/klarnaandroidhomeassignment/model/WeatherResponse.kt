package com.example.klarnaandroidhomeassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResponse(

//{"latitude":59.337239,"longitude":18.062381,"timezone":"Europe/Stockholm","currently":{}

    @Expose
    @SerializedName("latitude")
    val latitude: Double,

    @Expose
    @SerializedName("longitude")
    val longitude: Double,

    @Expose
    @SerializedName("timezone")
    val timezone: String,

    @Expose
    @SerializedName("currently")
    val currentlyWeather: CurrentWeather

)