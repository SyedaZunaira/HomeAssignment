package com.example.klarnaandroidhomeassignment.networking

import com.example.klarnaandroidhomeassignment.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface WeatherAPI {

    @GET("/forecast/2bb07c3bece89caf533ac9a5d23d8417/{latlong}?exclude=hourly,flags,daily,alerts")
    fun getWeatherUpdate(
        @Path("latlong") latlong: String
    )
            : Call<WeatherResponse>
}