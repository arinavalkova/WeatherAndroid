package com.nsu.ccfit.weatherapplication.presentation.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nsu.ccfit.weatherapplication.R

class CreateActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, CreateActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel: CreateViewModel by viewModels {
        CreateViewModelFactory(applicationContext)
    }

    private lateinit var nameText: TextView

    private lateinit var saveButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var content: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_city)

        viewModel.closeScreenEvent.observe(this) { closeScreen() }
        viewModel.toast.observe(this, ::showToast)
        viewModel.loading.observe(this) {
            content.isVisible = !it
            progressBar.isVisible = it
        }

        initViews()
    }

    private fun initViews() {
        nameText = findViewById(R.id.cityName)
        saveButton = findViewById(R.id.saveButton)

        progressBar = findViewById(R.id.progressBar)
        content = findViewById(R.id.content)

        saveButton.setOnClickListener {
            viewModel.createCity(
                nameText.text.toString(),
            )
        }
    }

    private fun closeScreen() {
        finish()
    }

    private fun showToast(text: String) {
        val toast: Toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}