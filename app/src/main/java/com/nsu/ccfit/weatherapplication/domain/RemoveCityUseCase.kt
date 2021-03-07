package com.nsu.ccfit.weatherapplication.domain

class RemoveCityUseCase(
    private val repository: CityRepository
) {

    operator fun invoke(cityName: String) = repository.deleteCity(cityName)
}