package com.nsu.ccfit.weatherapplication.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsu.ccfit.weatherapplication.data.City
import com.nsu.ccfit.weatherapplication.data.CityRepository

class ListViewModel(private val repository: CityRepository) : ViewModel() {

    val cityList = MutableLiveData<List<City>>()

    fun loadCity() {
        val city = repository.getCity()

        cityList.value = city
    }
}