package com.nsu.ccfit.weatherapplication.domain

import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import io.reactivex.Maybe

class CreateCityUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(name: String): Maybe<City> = cityRepository.createCity(name)
}