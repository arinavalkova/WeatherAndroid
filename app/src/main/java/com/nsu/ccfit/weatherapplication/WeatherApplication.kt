package com.nsu.ccfit.weatherapplication

import android.app.Application
import android.content.Context

class WeatherApplication : Application() {

    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = this.getAppContext()
    }

    fun getAppContext(): Context? {
        return appContext
    }
}