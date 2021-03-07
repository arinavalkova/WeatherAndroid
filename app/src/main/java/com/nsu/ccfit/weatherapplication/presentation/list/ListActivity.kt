package com.nsu.ccfit.weatherapplication.presentation.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.presentation.create.CreateActivity
import com.nsu.ccfit.weatherapplication.presentation.weather.WeatherActivity

class ListActivity : AppCompatActivity()  {

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(applicationContext)
    }

    private lateinit var cityList: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var createCityButton: FloatingActionButton

    private val adapter = CityAdapter {
        WeatherActivity.start(this, it)
    }

    private val swipeHelper = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            viewModel.removeCity(adapter.cities[position])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)
        createCityButton = findViewById(R.id.createCityButton)
        swipeRefresh = findViewById(R.id.swipeRefresh)

        viewModel.cityList.observe(this, ::bindCityList)

        viewModel.loading.observe(this) {
            cityList.isVisible = !it
            swipeRefresh.isRefreshing = it
        }

        cityList = findViewById(R.id.cityList)
        cityList.adapter = adapter

        ItemTouchHelper(swipeHelper).attachToRecyclerView(cityList)

        swipeRefresh.setOnRefreshListener {
            viewModel.loadCities()
        }

        createCityButton.setOnClickListener {
            CreateActivity.start(this)
        }
    }

    private fun bindCityList(list: List<String>) {
        adapter.cities = list
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCities()
    }
}