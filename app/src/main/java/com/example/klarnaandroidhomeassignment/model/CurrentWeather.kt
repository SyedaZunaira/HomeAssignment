package com.example.klarnaandroidhomeassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeather(

//latitude	:	59.337239
//longitude	:	18.062381
//timezone	:	Europe/Stockholm
//currently		{18}
//time	:	1575746014
//summary	:	Mostly Cloudy
//icon	:	partly-cloudy-night
//precipIntensity	:	0.001
//precipProbability	:	0.09
//precipType	:	rain
//temperature	:	34.91
//apparentTemperature	:	26.19
//dewPoint	:	31.56
//humidity	:	0.87
//pressure	:	991.2
//windSpeed	:	12.62
//windGust	:	25.15
//windBearing	:	289
//cloudCover	:	0.87
//uvIndex	:	0
//visibility	:	10
//ozone	:	307.3

    @Expose
    @SerializedName("time")
    val time: Double,

    @Expose
    @SerializedName("summary")
    val summary: String,

    @Expose
    @SerializedName("icon")
    val icon: String,

    @Expose
    @SerializedName("precipIntensity")
    val precipIntensity: Float,

    @Expose
    @SerializedName("precipProbability")
    val precipProbability: Float,

    @Expose
    @SerializedName("precipType")
    val precipType: String,

    @Expose
    @SerializedName("temperature")
    val temperature: Float,

    @Expose
    @SerializedName("apparentTemperature")
    val apparentTemperature: Float,

    @Expose
    @SerializedName("dewPoint")
    val dewPoint: Float,

    @Expose
    @SerializedName("humidity")
    val humidity: Float,

    @Expose
    @SerializedName("pressure")
    val pressure: Float,


    @Expose
    @SerializedName("windSpeed")
    val windSpeed: Float,

    @Expose
    @SerializedName("windGust")
    val windGust: Float,

    @Expose
    @SerializedName("windBearing")
    val windBearing: Int,

    @Expose
    @SerializedName("cloudCover")
    val cloudCover: Float,


    @Expose
    @SerializedName("uvIndex")
    val uvIndex: Float,

    @Expose
    @SerializedName("visibility")
    val visibility: Float,

    @Expose
    @SerializedName("ozone")
    val ozone: Float

)