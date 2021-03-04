package com.nsu.ccfit.weatherapplication.domain

interface CityRepository {

    fun getCities(): List<City>

    fun getCity(id: Long): City?

    fun setCity(city: City)
}