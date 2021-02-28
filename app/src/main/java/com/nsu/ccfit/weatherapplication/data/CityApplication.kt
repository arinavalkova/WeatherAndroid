package com.nsu.ccfit.weatherapplication.data

import android.app.Application

class CityApplication : Application() {

    lateinit var cityRepository: CityRepository

    override fun onCreate() {
        super.onCreate()
        cityRepository = CityRepository()
    }
}