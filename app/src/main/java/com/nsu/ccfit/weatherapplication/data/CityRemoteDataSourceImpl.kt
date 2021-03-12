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
    @SuppressLint("StaticFieldLeak") private val applicationContext: Context,
    private val database: SQLiteDatabase
) : CityDataSource, BaseViewModel() {

    val TABLE_NAME = "cities"

    val KEY_ID = "id"
    val KEY_NAME = "name"

    override fun getCities(): List<String> {

        val cities: MutableList<String> = mutableListOf()

        val cursor: Cursor = database.query(TABLE_NAME, null, null, null, null, null, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                cities.add(cursor.getString(cursor.getColumnIndex(KEY_NAME)))
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
        database.delete(
            TABLE_NAME,
            "$KEY_NAME='$cityName'",
            null
        )
        return Completable.complete()
    }

    override fun createCity(cityName: String): Maybe<City>{
        val updatedValues = ContentValues()

        updatedValues.put(KEY_NAME, cityName)

        api.getCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!isInDataBase(cityName)) {
                    database.insert(TABLE_NAME, null, updatedValues)
                }
            }, {})
            .untilDestroy()

        return api.getCity(cityName)
            .subscribeOn(Schedulers.io())
    }

    @SuppressLint("Recycle")
    private fun isInDataBase(cityName: String): Boolean {
        val cursor: Cursor =
            database.query(TABLE_NAME, null, "$KEY_NAME=?",
                arrayOf(cityName), null, null, null, null)
        return cursor.count != 0
    }
}