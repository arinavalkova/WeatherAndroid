package com.nsu.ccfit.weatherapplication.presentation.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.domain.api.openweather.gson.City

class WeatherActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, cityName: String) {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra(EXTRA_ID, cityName)
            context.startActivity(intent)
        }
    }

    private val viewModel: WeatherViewModel by viewModels {
        val cityName = intent.getStringExtra(EXTRA_ID)
        WeatherViewModelFactory(applicationContext, cityName!!)
    }

    private lateinit var nameText: TextView
    private lateinit var tempText: TextView
    private lateinit var feelsLikeText: TextView
    private lateinit var humidityText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var content: ConstraintLayout
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)

        viewModel.city.observe(this, ::renderCityDetailsState)
        viewModel.closeScreenEvent.observe(this) { closeScreen() }
        viewModel.loading.observe(this) {
            content.isVisible = !it
            progressBar.isVisible = it
        }

        initViews()
    }

    private fun renderCityDetailsState(city: City) {
        nameText.inputType = EditorInfo.TYPE_NULL
        tempText.inputType = EditorInfo.TYPE_NULL
        feelsLikeText.inputType = EditorInfo.TYPE_NULL
        humidityText.inputType = EditorInfo.TYPE_NULL
        descriptionText.inputType = EditorInfo.TYPE_NULL

        nameText.setText(getString(R.string.name_format, city.name))
        tempText.setText(getString(R.string.temp_format, city.main.temp))
        feelsLikeText.setText(getString(R.string.feels_like_format, city.main.feels_like))
        humidityText.setText(getString(R.string.humidity_format, city.main.humidity))
        descriptionText.setText(getString(R.string.descriprion_format, city.weather.get(0).description))
    }

    private fun initViews() {
        nameText = findViewById(R.id.nameText)
        tempText = findViewById(R.id.tempText)
        feelsLikeText = findViewById(R.id.feelsLikeText)
        humidityText = findViewById(R.id.humidityText)
        descriptionText = findViewById(R.id.description)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            closeScreen()
        }

        progressBar = findViewById(R.id.progressBar)
        content = findViewById(R.id.content)
    }

    private fun closeScreen() {
        finish()
    }
}