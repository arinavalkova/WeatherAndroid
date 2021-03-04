package com.nsu.ccfit.weatherapplication.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.P)
class CityDataBaseHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    val TABLE_NAME = "cities"

    val KEY_ID = "id"
    val KEY_NAME = "name"
    val KEY_DESCRIPTION = "description"
    val KEY_TEMP = "temp"
    val KEY_FEELS_LIKE = "feels_like"
    val KEY_HUMIDITY = "humidity"

    override fun onCreate(dataBase: SQLiteDatabase?) {
        dataBase?.execSQL(
            "create table " + TABLE_NAME + "("
                    + KEY_ID + " integer primary key,"
                    + KEY_NAME + " text,"
                    + KEY_DESCRIPTION + " text,"
                    + KEY_TEMP + " real,"
                    + KEY_FEELS_LIKE + " real,"
                    + KEY_HUMIDITY + " integer"
                    + ");"
        )

        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (0,'Novosibirsk','My city',0.0,0.0,0);")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (1,'Moscow','Nick city',0.0,0.0,0);")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (2,'Tomsk','No description',0.0,0.0,0);")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (3,'Berdsk','No description',0.0,0.0,0);")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (4,'Krasnodar','No description',0.0,0.0,0);")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (5,'Murmansk','No description',0.0,0.0,0);")
    }

    override fun onUpgrade(dataBase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        dataBase?.execSQL("drop table if exists $TABLE_NAME")

        onCreate(dataBase)
    }
}