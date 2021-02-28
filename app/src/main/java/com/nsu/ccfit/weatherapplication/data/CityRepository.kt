package com.nsu.ccfit.weatherapplication.data

class CityRepository {
    private val cities = mutableListOf(
        City(id = 0, name = "Novosibirsk", description = "My city"),
        City(id = 1, name = "Moscow", description = "Nick city"),
        City(id = 2, name = "Tomsk"),
        City(id = 3, name = "Berdsk"),
        City(id = 4, name = "Krasnodar"),
        City(id = 5, name = "Murmansk")
    )

    fun getCity(): List<City> = cities

    fun getCity(id: Long): City? = cities.firstOrNull { it.id == id }

    fun setCity(city: City) {
        val editedCityIndex = cities.indexOfFirst { it.id == city.id }
        if (editedCityIndex >= 0) {
            cities[editedCityIndex] = city
        }
    }
}