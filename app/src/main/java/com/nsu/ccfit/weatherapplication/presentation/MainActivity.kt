package com.nsu.ccfit.weatherapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nsu.ccfit.weatherapplication.R
import com.nsu.ccfit.weatherapplication.presentation.list.ListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var startButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleStartButton()
    }

    private fun handleStartButton() {
        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            this.startActivity(intent)
            finish()
        }
    }
}