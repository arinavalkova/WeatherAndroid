package com.nsu.ccfit.weatherapplication.presentation.list

import androidx.lifecycle.MutableLiveData
import com.nsu.ccfit.weatherapplication.domain.GetCitiesUseCase
import com.nsu.ccfit.weatherapplication.domain.RemoveCityUseCase
import com.nsu.ccfit.weatherapplication.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class ListViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val removePersonUseCase: RemoveCityUseCase
) : BaseViewModel() {

    val cityList = MutableLiveData<List<String>>()
    val loading = MutableLiveData(true)

    fun loadCities() {
        loading.value = true
        cityList.value = getCitiesUseCase()
        loading.value = false
    }

    fun removeCity(cityName: String) {
        loading.value = true
        removePersonUseCase(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadCities()
            }, {
                loadCities()
            })
            .untilDestroy()
    }
}