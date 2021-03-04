package com.nsu.ccfit.weatherapplication.data

import com.nsu.ccfit.weatherapplication.domain.City

interface CityDataSource {

    fun getCities(): List<City>

    fun getCity(id: Long): City?

    fun setCity(city: City)
}