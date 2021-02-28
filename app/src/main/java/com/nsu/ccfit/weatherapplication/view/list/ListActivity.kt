package com.nsu.ccfit.weatherapplication.view.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.data.CityApplication
import com.nsu.ccfit.weatherapplication.data.CityRepository
import com.nsu.ccfit.weatherapplication.view.weather.WeatherActivity

class ListActivity : AppCompatActivity()  {
    private lateinit var cityRepository: CityRepository
    private lateinit var cityList: RecyclerView

    private val adapter = CityAdapter {
        WeatherActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityRepository = (application as CityApplication).cityRepository
        setContentView(R.layout.list_activity)

        cityList = findViewById(R.id.cityList)
        cityList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.cities = cityRepository.getCity()
    }
}