package com.nsu.ccfit.weatherapplication.domain

import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import io.reactivex.Completable
import io.reactivex.Maybe

interface CityRepository {

    fun getCities(): List<String>

    fun getCity(cityName: String): Maybe<City>

    fun deleteCity(cityName: String): Completable

    fun createCity(cityName: String): Maybe<City>
}