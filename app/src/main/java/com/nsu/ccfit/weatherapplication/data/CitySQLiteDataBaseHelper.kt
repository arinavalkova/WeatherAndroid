package com.nsu.ccfit.weatherapplication.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.P)
class CitySQLiteDataBaseHelper(
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
    }

    override fun onUpgrade(dataBase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        dataBase?.execSQL("drop table if exists $TABLE_NAME")

        onCreate(dataBase)
    }
}