package com.nsu.ccfit.weatherapplication.api.openweathermap.request

import com.google.gson.Gson
import com.nsu.ccfit.weatherapplication.api.openweathermap.request.gson.WeatherResult
import java.net.URL

class WeatherRequest {
    fun getWeatherResult(city: String): WeatherResult {
        val response =
            URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=cffbe4b961ec581d11adb3dbc92cdde0")
                .readText()
        return Gson().fromJson(response, WeatherResult::class.java)
    }   
}