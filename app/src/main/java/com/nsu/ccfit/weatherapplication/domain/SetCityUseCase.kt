package com.nsu.ccfit.weatherapplication.domain

class SetCityUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(city: City) = cityRepository.setCity(city)
}