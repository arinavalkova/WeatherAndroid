package com.nsu.ccfit.weatherapplication.data

import com.nsu.ccfit.weatherapplication.domain.CityRepository

class CityRepositoryImpl(private val cityDataSource: CityDataSource) : CityRepository {

    override fun getCities() = cityDataSource.getCities()

    override fun getCity(cityName: String) = cityDataSource.getCity(cityName)

    override fun deleteCity(cityName: String) = cityDataSource.deleteCity(cityName)

    override fun createCity(cityName: String) = cityDataSource.createCity(cityName)

}