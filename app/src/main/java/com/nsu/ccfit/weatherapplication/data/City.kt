package com.nsu.ccfit.weatherapplication.data

data class City(
    val id: Long,
    val name: String,
    val description: String? = null
)