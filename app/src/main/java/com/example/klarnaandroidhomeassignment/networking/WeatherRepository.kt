package com.example.klarnaandroidhomeassignment.networking

import androidx.lifecycle.MutableLiveData
import com.example.klarnaandroidhomeassignment.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {

    private val weatherAPI: WeatherAPI

    fun getWeatherUpdate(latLong: String?): MutableLiveData<WeatherResponse> {

        val weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()
        latLong?.let {

            weatherAPI.getWeatherUpdate(it).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(
                    call: Call<WeatherResponse?>?,
                    response: Response<WeatherResponse?>
                ) {
                    if (response.isSuccessful()) {
                        weatherData.setValue(response.body())
                    }
                }

                override fun onFailure(call: Call<WeatherResponse?>?, t: Throwable?) {
                    weatherData.setValue(null)
                }
            })
        }
        return weatherData
    }

    companion object {
        private var weatherRepository: WeatherRepository? = null
        val instance: WeatherRepository?
            get() {
                if (weatherRepository == null) {
                    weatherRepository = WeatherRepository()
                }
                return weatherRepository
            }
    }

    init {
        weatherAPI = RetrofitService.cteateService(WeatherAPI::class.java)
    }
}