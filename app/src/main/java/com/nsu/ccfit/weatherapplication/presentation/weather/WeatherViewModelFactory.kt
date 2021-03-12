package com.nsu.ccfit.weatherapplication.presentation.weather

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nsu.ccfit.weatherapplication.data.CityRemoteDataSourceImpl
import com.nsu.ccfit.weatherapplication.data.CityRepositoryImpl
import com.nsu.ccfit.weatherapplication.data.CitySQLiteDataBaseHelper
import com.nsu.ccfit.weatherapplication.data.RetrofitHolder
import com.nsu.ccfit.weatherapplication.domain.GetCityUseCase

class WeatherViewModelFactory(private val applicationContext: Context, private val cityName: String) : ViewModelProvider.Factory {

    private val DATABASE_NAME = "citiesDB"
    private val DATABASE_VERSION = 1

    @RequiresApi(Build.VERSION_CODES.P)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       val dataBaseHelper = CitySQLiteDataBaseHelper(
            applicationContext, DATABASE_NAME, null, DATABASE_VERSION
        )
        val database: SQLiteDatabase = dataBaseHelper.writableDatabase
        val cityDataSource = CityRemoteDataSourceImpl(RetrofitHolder.cityApi, applicationContext, database)
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCityUseCase = GetCityUseCase(cityRepository)

        return modelClass
            .getConstructor(
                GetCityUseCase::class.java,
                String::class.java
            )
            .newInstance(getCityUseCase, cityName)
    }
}