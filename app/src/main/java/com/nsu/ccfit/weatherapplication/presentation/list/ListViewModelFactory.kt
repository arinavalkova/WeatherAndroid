package com.nsu.ccfit.weatherapplication.presentation.list

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nsu.ccfit.weatherapplication.data.CityRemoteDataSourceImpl
import com.nsu.ccfit.weatherapplication.data.CityRepositoryImpl
import com.nsu.ccfit.weatherapplication.domain.GetCitiesUseCase

class ListViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val cityDataSource = CityRemoteDataSourceImpl(applicationContext)
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCitiesUseCase = GetCitiesUseCase(cityRepository)

        return modelClass
            .getConstructor(GetCitiesUseCase::class.java)
            .newInstance(getCitiesUseCase)
    }
}