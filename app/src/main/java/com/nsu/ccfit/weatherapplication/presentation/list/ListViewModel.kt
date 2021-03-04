package com.nsu.ccfit.weatherapplication.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsu.ccfit.weatherapplication.domain.City
import com.nsu.ccfit.weatherapplication.domain.GetCitiesUseCase

class ListViewModel(private val getCitiesUseCase: GetCitiesUseCase) : ViewModel() {

    val cityList = MutableLiveData<List<City>>()

    fun loadCity() {
        val city = getCitiesUseCase()

        cityList.value = city
    }
}