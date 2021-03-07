package com.nsu.ccfit.weatherapplication.domain

class GetCityUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(cityName: String) = cityRepository.getCity(cityName)
}