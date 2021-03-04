package com.nsu.ccfit.weatherapplication.domain.api.openweather.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("description")
    @Expose
    private val description: String? = null

    fun getDescription(): String? {
        return description
    }
}