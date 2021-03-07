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

    override fun onCreate(dataBase: SQLiteDatabase?) {
        dataBase?.execSQL(
            "create table " + TABLE_NAME + "("
                    + KEY_ID + " integer primary key AUTOINCREMENT,"
                    + KEY_NAME + " text"
                    + ");"
        )

        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (0,'Novosibirsk');")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (1,'Moscow');")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (2,'Tomsk');")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (3,'Berdsk');")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (4,'Krasnodar');")
        dataBase?.execSQL("INSERT INTO $TABLE_NAME Values (5,'Murmansk');")
    }

    override fun onUpgrade(dataBase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        dataBase?.execSQL("drop table if exists $TABLE_NAME")

        onCreate(dataBase)
    }
}