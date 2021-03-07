package com.nsu.ccfit.weatherapplication.domain.api.openweather.gson

data class City (
    val id: Long,
    val name: String,
    val main: Main,
    val weather: List<Weather>
)

