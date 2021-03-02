package com.nsu.ccfit.weatherapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nsu.ccfit.weatherapplication.view.list.ListActivity

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