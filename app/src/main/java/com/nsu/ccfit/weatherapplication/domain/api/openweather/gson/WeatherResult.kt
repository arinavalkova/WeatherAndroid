package com.nsu.ccfit.weatherapplication.domain.api.openweather.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResult {
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null

    @SerializedName("main")
    @Expose
    val main: Main? = null

    @SerializedName("name")
    @Expose
    val name: String? = null
}