package com.nsu.ccfit.weatherapplication.presentation.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.domain.City

class WeatherActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private val viewModel: WeatherViewModel by viewModels {
        val id = intent.getLongExtra(EXTRA_ID, 0)
        WeatherViewModelFactory(applicationContext, id)
    }

    private lateinit var nameText: TextView
    private lateinit var tempText: TextView
    private lateinit var feelsLikeText: TextView
    private lateinit var humidityText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var descriptionInput: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)

        viewModel.city.observe(this, ::bindCity)
        viewModel.closeScreenEvent.observe(this) { closeScreen() }
        viewModel.weatherRequestEvent.observe(this, ::bindCity)

        initViews()

        viewModel.start()
    }

    private fun initViews() {
        nameText = findViewById(R.id.nameText)
        descriptionInput = findViewById(R.id.descriptionInput)
        tempText = findViewById(R.id.tempText)
        feelsLikeText = findViewById(R.id.feelsLikeText)
        humidityText = findViewById(R.id.humidityText)
        descriptionText = findViewById(R.id.descriptionOutput)
        saveButton = findViewById(R.id.saveButton)
    }

    private fun bindCity(city: City) {

        nameText.text = getString(R.string.name_format, city.name)
        descriptionInput.setText(city.description ?: getString(R.string.description_absent))
        tempText.text = getString(R.string.temp_format, city.temp)
        feelsLikeText.text =
            getString(R.string.feels_like_format, city.feelsLike)
        humidityText.text = getString(R.string.humidity_format, city.humidity)
        descriptionText.text =
            getString(R.string.descriprion_format, city.weatherDescription)

        saveButton.setOnClickListener {
            val editedCity = city.copy(description = descriptionInput.text.toString())
            viewModel.saveCity(editedCity)
            finish()
        }
    }

    private fun closeScreen() {
        finish()
    }
}