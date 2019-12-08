package com.example.klarnaandroidhomeassignment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.klarnaandroidhomeassignment.model.CurrentWeather
import com.example.klarnaandroidhomeassignment.model.WeatherResponse
import com.example.klarnaandroidhomeassignment.networking.WeatherRepository
import com.mayowa.android.locationwithlivedata.LocationLiveData


class WeatherViewModel(application: Application)  : AndroidViewModel(application) {

     var mutableLiveData: MutableLiveData<WeatherResponse>? = null
    private var weatherRepository: WeatherRepository? = null

    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData


    fun init(latLong : String) {
        if (mutableLiveData != null) {
            return
        }
        weatherRepository = WeatherRepository()
        mutableLiveData = weatherRepository!!.getWeatherUpdate(latLong)
    }

    fun getWeatherRepository(): LiveData<WeatherResponse>? {
        return mutableLiveData
    }
}