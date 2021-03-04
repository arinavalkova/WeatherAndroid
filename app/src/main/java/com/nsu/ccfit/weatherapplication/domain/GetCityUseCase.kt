package com.nsu.ccfit.weatherapplication.domain

class GetCityUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(id: Long): City? = cityRepository.getCity(id)
}