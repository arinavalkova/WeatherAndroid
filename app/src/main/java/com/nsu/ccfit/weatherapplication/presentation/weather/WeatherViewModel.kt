package com.nsu.ccfit.weatherapplication.presentation.weather

import androidx.lifecycle.MutableLiveData
import com.nsu.ccfit.weatherapplication.domain.GetCityUseCase
import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import com.nsu.ccfit.weatherapplication.presentation.BaseViewModel
import com.nsu.ccfit.weatherapplication.presentation.LiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers

class WeatherViewModel(
    getCityUseCase: GetCityUseCase,
    cityName: String
) : BaseViewModel() {

    val city = MutableLiveData<City>()
    val closeScreenEvent = LiveEvent()
    val loading = MutableLiveData(false)

    init {
            loading.value = true
            getCityUseCase(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading.value = false
                    this.city.value = it
                }, {
                    closeScreenEvent(Unit)
                })
                .untilDestroy()
    }
}