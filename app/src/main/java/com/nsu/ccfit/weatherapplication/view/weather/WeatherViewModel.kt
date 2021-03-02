package com.nsu.ccfit.weatherapplication.view.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsu.ccfit.weatherapplication.LiveEvent
import com.nsu.ccfit.weatherapplication.api.openweathermap.request.WeatherRequest
import com.nsu.ccfit.weatherapplication.data.City
import com.nsu.ccfit.weatherapplication.data.CityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: CityRepository,
    id: Long
) : ViewModel() {

    val city = MutableLiveData<City>()
    val closeScreenEvent = LiveEvent()
    val weatherRequestEvent = MutableLiveData<City>()
    var id: Long;

    init {
        val city = repository.getCity(id)
        this.id = id

        if (city != null) {
            this.city.value = city
        } else {
            closeScreenEvent(Unit)
        }
    }

    fun saveCity(editedCity: City) {
        repository.setCity(editedCity)
        closeScreenEvent()
    }

    fun weatherRequest() {
        val editedCity = repository.getCity(id)
        val weatherResult = CoroutineScope(Dispatchers.IO).async {
            WeatherRequest().getWeatherResult(editedCity?.name!!)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val result = weatherResult.await()
            editedCity?.temp = result.main?.getTemp()
            editedCity?.feelsLike = result.main?.getFeelsLike()
            editedCity?.humidity = result.main?.getHumidity()
            editedCity?.weatherDescription = result.weather?.get(0)?.getDescription()

            repository.setCity(editedCity!!)
            weatherRequestEvent.postValue(editedCity!!)
        }
    }
}