package com.nsu.ccfit.weatherapplication.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsu.ccfit.weatherapplication.domain.City
import com.nsu.ccfit.weatherapplication.domain.GetCityUseCase
import com.nsu.ccfit.weatherapplication.domain.SetCityUseCase
import com.nsu.ccfit.weatherapplication.domain.api.openweather.WeatherRequest
import com.nsu.ccfit.weatherapplication.presentation.LiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getCityUseCase: GetCityUseCase,
    private val setCityUseCase: SetCityUseCase,
    id: Long
) : ViewModel() {

    val city = MutableLiveData<City>()
    val closeScreenEvent = LiveEvent()
    val weatherRequestEvent = MutableLiveData<City>()
    var id: Long

    init {
        val city = getCityUseCase(id)
        this.id = id

        if (city != null) {
            this.city.value = city!!
        } else {
            closeScreenEvent(Unit)
        }
    }

    fun saveCity(editedCity: City) {
        setCityUseCase(editedCity)
        closeScreenEvent()
    }

    fun start() {
        weatherRequest()
    }

    private fun weatherRequest() {
        val editedCity = getCityUseCase(id)
        val weatherResult = CoroutineScope(Dispatchers.IO).async {
            WeatherRequest().getWeatherResult(editedCity?.name!!)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val result = weatherResult.await()
            editedCity?.temp = result.main?.getTemp()
            editedCity?.feelsLike = result.main?.getFeelsLike()
            editedCity?.humidity = result.main?.getHumidity()
            editedCity?.weatherDescription = result.weather?.get(0)?.getDescription()

            setCityUseCase(editedCity!!)
            weatherRequestEvent.postValue(editedCity!!)
        }
    }
}