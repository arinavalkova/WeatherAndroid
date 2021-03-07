package com.nsu.ccfit.weatherapplication.presentation.create

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nsu.ccfit.weatherapplication.data.CityRemoteDataSourceImpl
import com.nsu.ccfit.weatherapplication.data.CityRepositoryImpl
import com.nsu.ccfit.weatherapplication.data.RetrofitHolder
import com.nsu.ccfit.weatherapplication.domain.CreateCityUseCase

class CreateViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val cityDataSource = CityRemoteDataSourceImpl(RetrofitHolder.cityApi, applicationContext)
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val createCityUseCase = CreateCityUseCase(cityRepository)

        return modelClass
            .getConstructor(
                CreateCityUseCase::class.java,
            )
            .newInstance(createCityUseCase)
    }
}