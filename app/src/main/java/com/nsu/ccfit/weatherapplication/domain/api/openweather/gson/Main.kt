package com.nsu.ccfit.weatherapplication.domain.api.openweather.gson

data class Main (
    val temp: Double,
    val feels_like: Double,
    val humidity: Int
)