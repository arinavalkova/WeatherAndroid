package com.nsu.ccfit.weatherapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City
import com.nsu.ccfit.weatherapplication.presentation.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@RequiresApi(Build.VERSION_CODES.P)
class CityRemoteDataSourceImpl(
    private val api: CityApi,
    @SuppressLint("StaticFieldLeak") private val applicationContext: Context
) : CityDataSource, BaseViewModel() {

    private val DATABASE_NAME = "citiesDB"
    private val TABLE_NAME = "cities"
    private val DATABASE_VERSION = 1

    private val dataBaseHelper = CityDataBaseHelper(
        applicationContext, DATABASE_NAME, null, DATABASE_VERSION
    )
    private val database: SQLiteDatabase = dataBaseHelper.writableDatabase

    override fun getCities(): List<String> {

        val cities: MutableList<String> = mutableListOf()

        val cursor: Cursor = database.query(TABLE_NAME, null, null, null, null, null, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                cities.add(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_NAME)))
            } while (cursor.moveToNext())
        }
        cursor.close()

        return cities.toList()
    }

    override fun getCity(cityName: String): Maybe<City> {

        return api.getCity(cityName)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteCity(cityName: String): Completable {
        database.delete(dataBaseHelper.TABLE_NAME, dataBaseHelper.KEY_NAME + "='" + cityName +"'", null)
        return Completable.complete()
    }

    override fun createCity(cityName: String): Maybe<City>{
        val updatedValues = ContentValues()

        updatedValues.put(dataBaseHelper.KEY_NAME, cityName)

        api.getCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                database.insert(TABLE_NAME, null, updatedValues)
            }, {
            })
            .untilDestroy()

        return api.getCity(cityName)
            .subscribeOn(Schedulers.io())
    }
}