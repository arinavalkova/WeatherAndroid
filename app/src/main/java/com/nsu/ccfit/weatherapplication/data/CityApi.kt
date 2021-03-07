package com.nsu.ccfit.weatherapplication.data

import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("weather")
    fun getCity(
        @Query("q") city_name: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = "cffbe4b961ec581d11adb3dbc92cdde0"
    ): Maybe<City>
}