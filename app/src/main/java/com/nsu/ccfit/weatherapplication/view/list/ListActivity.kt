package com.nsu.ccfit.weatherapplication.view.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.data.City
import com.nsu.ccfit.weatherapplication.data.CityApplication
import com.nsu.ccfit.weatherapplication.data.CityRepository
import com.nsu.ccfit.weatherapplication.view.weather.WeatherActivity

class ListActivity : AppCompatActivity()  {

    private val viewModel: ListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                modelClass
                    .getConstructor(CityRepository::class.java)
                    .newInstance(
                        (application as CityApplication).cityRepository
                    )
        }
    }

    private lateinit var cityList: RecyclerView

    private val adapter = CityAdapter {
        WeatherActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        viewModel.cityList.observe(this, ::bindCityList)

        cityList = findViewById(R.id.cityList)
        cityList.adapter = adapter
    }

    private fun bindCityList(list: List<City>) {
        adapter.cities = list
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCity()
    }
}