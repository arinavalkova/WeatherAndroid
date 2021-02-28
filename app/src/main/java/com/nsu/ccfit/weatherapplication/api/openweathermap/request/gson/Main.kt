package com.nsu.ccfit.weatherapplication.api.openweathermap.request.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    private val temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    private val feelsLike: Double? = null

    @SerializedName("humidity")
    @Expose
    private val humidity: Int? = null

    fun getTemp(): Double? {
        return temp
    }

    fun getHumidity(): Int? {
        return humidity
    }

    fun getFeelsLike(): Double? {
        return feelsLike
    }
}