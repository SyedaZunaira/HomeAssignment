package com.example.klarnaandroidhomeassignment.networking

import androidx.lifecycle.MutableLiveData
import com.example.klarnaandroidhomeassignment.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {

    private val weatherAPI = RetrofitService.cteateService(WeatherAPI::class.java)

    fun getWeatherUpdate(latLong: String?): MutableLiveData<WeatherResponse> {

        val weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()
        latLong?.let {

            weatherAPI.getWeatherUpdate(it).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(
                    call: Call<WeatherResponse?>?,
                    response: Response<WeatherResponse?>
                ) {
                    if (response.isSuccessful) {
                        weatherData.value = response.body()
                    }
                }
                override fun onFailure(call: Call<WeatherResponse?>?, t: Throwable?) {
                    weatherData.value = null
                }
            })
        }
        return weatherData
    }
}