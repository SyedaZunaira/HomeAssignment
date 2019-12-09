package com.example.klarnaandroidhomeassignment.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("currently")
    val currentlyWeather: CurrentWeather
)