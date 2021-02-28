package com.nsu.ccfit.weatherapplication.view.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.api.openweathermap.request.WeatherRequest
import com.nsu.ccfit.weatherapplication.data.CityApplication
import com.nsu.ccfit.weatherapplication.data.CityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private lateinit var cityRepository: CityRepository

    private lateinit var nameText: TextView
    private lateinit var tempText: TextView
    private lateinit var feelsLikeText: TextView
    private lateinit var humidityText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var descriptionInput: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityRepository = (application as CityApplication).cityRepository
        setContentView(R.layout.weather_activity)

        initViews()
    }

    private fun initViews() {
        val id = intent.getLongExtra(EXTRA_ID, 0)
        val city = cityRepository.getCity(id)

        if (city != null) {
            nameText = findViewById(R.id.nameText)
            descriptionInput = findViewById(R.id.descriptionInput)

            saveButton = findViewById(R.id.saveButton)

            nameText.text = getString(R.string.name_format, city.name)
            descriptionInput.setText(city.description ?: getString(R.string.description_absent))

            saveButton.setOnClickListener {
                val editedPerson = city.copy(description = descriptionInput.text.toString())
                cityRepository.setCity(editedPerson)
                finish()
            }

            handleWeatherRequest(city.name)
        } else {
            finish()
        }
    }

    @SuppressLint("StringFormatMatches", "StringFormatInvalid")
    private fun handleWeatherRequest(cityName: String) {
        tempText = findViewById(R.id.tempText)
        feelsLikeText = findViewById(R.id.feelsLikeText)
        humidityText = findViewById(R.id.humidityText)
        descriptionText = findViewById(R.id.descriptionOutput)

        val weatherResult =  CoroutineScope(Dispatchers.IO).async() {
            WeatherRequest().getWeatherResult(cityName)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val result = weatherResult.await()
            runOnUiThread {
                tempText.text = getString(R.string.temp_format, result.main?.getTemp())
                feelsLikeText.text = getString(R.string.feels_like_format, result.main?.getFeelsLike())
                humidityText.text = getString(R.string.humidity_format, result.main?.getHumidity())
                descriptionText.text = getString(R.string.descriprion_format, result.weather?.get(0)?.getDescription())
            }
        }
    }
}