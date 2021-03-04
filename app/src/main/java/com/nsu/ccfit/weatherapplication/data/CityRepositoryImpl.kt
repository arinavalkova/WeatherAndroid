package com.nsu.ccfit.weatherapplication.data

import com.nsu.ccfit.weatherapplication.domain.City
import com.nsu.ccfit.weatherapplication.domain.CityRepository

class CityRepositoryImpl(private val cityDataSource: CityDataSource) : CityRepository {

    override fun getCities(): List<City> = cityDataSource.getCities()

    override fun getCity(id: Long): City? = cityDataSource.getCity(id)

    override fun setCity(city: City) = cityDataSource.setCity(city)

}