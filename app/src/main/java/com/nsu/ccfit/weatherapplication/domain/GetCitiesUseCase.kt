package com.nsu.ccfit.weatherapplication.domain

class GetCitiesUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(): List<City> = cityRepository.getCities()
}