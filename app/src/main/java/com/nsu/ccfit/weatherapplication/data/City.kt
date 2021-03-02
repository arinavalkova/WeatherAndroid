package com.nsu.ccfit.weatherapplication.data

data class City(
    val id: Long,
    val name: String,
    val description: String? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var humidity: Int? = null,
    var weatherDescription: String? = null
)