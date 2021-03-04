package com.nsu.ccfit.weatherapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.nsu.ccfit.weatherapplication.domain.City


@RequiresApi(Build.VERSION_CODES.P)
class CityRemoteDataSourceImpl(applicationContext: Context) : CityDataSource {

    private val DATABASE_NAME = "citiesDB"
    private val TABLE_NAME = "cities"
    private val DATABASE_VERSION = 1

    private val dataBaseHelper = CityDataBaseHelper(
        applicationContext, DATABASE_NAME, null, DATABASE_VERSION
    )
    private val database: SQLiteDatabase = dataBaseHelper.writableDatabase

    override fun getCities(): List<City> {

        val cities: MutableList<City> = mutableListOf()

        val cursor: Cursor = database.query(TABLE_NAME, null, null, null, null, null, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                cities.add(
                    City(
                        cursor.getLong(cursor.getColumnIndex(dataBaseHelper.KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(dataBaseHelper.KEY_TEMP)),
                        cursor.getDouble(cursor.getColumnIndex(dataBaseHelper.KEY_FEELS_LIKE)),
                        cursor.getInt(cursor.getColumnIndex(dataBaseHelper.KEY_HUMIDITY))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return cities
    }

    @SuppressLint("Recycle")
    override fun getCity(id: Long): City {
        val cursor: Cursor = database.query(
            TABLE_NAME, null, "${dataBaseHelper.KEY_ID}=$id",
            null, null, null, null
        )
        cursor.moveToFirst()

        val city = City(
            cursor.getLong(cursor.getColumnIndex(dataBaseHelper.KEY_ID)),
            cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_NAME)),
            cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_DESCRIPTION)),
            cursor.getDouble(cursor.getColumnIndex(dataBaseHelper.KEY_TEMP)),
            cursor.getDouble(cursor.getColumnIndex(dataBaseHelper.KEY_FEELS_LIKE)),
            cursor.getInt(cursor.getColumnIndex(dataBaseHelper.KEY_HUMIDITY))
        )

        cursor.close()
        return city
    }

    override fun setCity(city: City) {
        val updatedValues = ContentValues()

        updatedValues.put(dataBaseHelper.KEY_ID, city.id)
        updatedValues.put(dataBaseHelper.KEY_NAME, city.name)
        updatedValues.put(dataBaseHelper.KEY_TEMP, city.temp)
        updatedValues.put(dataBaseHelper.KEY_FEELS_LIKE, city.feelsLike)
        updatedValues.put(dataBaseHelper.KEY_HUMIDITY, city.humidity)
        updatedValues.put(dataBaseHelper.KEY_DESCRIPTION, city.description)

        database.update(TABLE_NAME, updatedValues, "${dataBaseHelper.KEY_ID}=${city.id}", null)
    }
}