package com.example.klarnaandroidhomeassignment.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(

    @SerializedName("time")
    val time: Double,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("precipIntensity")
    val precipIntensity: Float,

    @SerializedName("precipProbability")
    val precipProbability: Float,

    @SerializedName("precipType")
    val precipType: String,

    @SerializedName("temperature")
    val temperature: Float,

    @SerializedName("apparentTemperature")
    val apparentTemperature: Float,

    @SerializedName("dewPoint")
    val dewPoint: Float,

    @SerializedName("humidity")
    val humidity: Float,

    @SerializedName("pressure")
    val pressure: Float,

    @SerializedName("windSpeed")
    val windSpeed: Float,

    @SerializedName("windGust")
    val windGust: Float,

    @SerializedName("windBearing")
    val windBearing: Int,

    @SerializedName("cloudCover")
    val cloudCover: Float,

    @SerializedName("uvIndex")
    val uvIndex: Float,

    @SerializedName("visibility")
    val visibility: Float,

    @SerializedName("ozone")
    val ozone: Float

)