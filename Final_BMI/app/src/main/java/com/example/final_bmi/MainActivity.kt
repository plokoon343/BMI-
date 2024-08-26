package com.example.final_bmi

import android.os.Bundle
import android.os.health.HealthStats
import android.widget.NumberPicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var bmiResult: TextView
    private lateinit var healthStats: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bmiResult = findViewById(R.id.bmi_result)
        healthStats = findViewById(R.id.health_status)

        val height = findViewById<NumberPicker>(R.id.height)
        height.minValue = 80
        height.maxValue = 250
        height.value = 150

        val weight = findViewById<NumberPicker>(R.id.weight)
        weight.minValue = 30
        weight.maxValue = 200
        weight.value = 70

        // Assuming you want to calculate BMI when the height or weight changes
        height.setOnValueChangedListener { _, _, _ ->
            Cal_BMI(height.value, weight.value)
        }
        weight.setOnValueChangedListener { _, _, _ ->
            Cal_BMI(height.value, weight.value)
        }
    }

    private fun Cal_BMI(height: Int, weight: Int) {
        val bmi = weight / ((height / 100f) * (height / 100f))
       bmiResult.text = "BMI: " + String.format("%.2f", bmi)
//
//        healthStats.text = when {
//            bmi < 18.5 -> "Underweight"
//            bmi < 25 -> "Healthy"
//            bmi < 30 -> "Overweight"
//            else -> "Obesity"
    healthStats.text = when {
        bmi < 16 -> "Severely Underweight"
        bmi < 18.5 -> "Underweight"
        bmi < 25 -> "Normal (Healthy)"
        bmi < 30 -> "Overweight"
        bmi < 35 -> "Obesity Class I (Moderate)"
        bmi < 40 -> "Obesity Class II (Severe)"
        else -> "Obesity Class III (Very Severe or Morbidly Obese)"
        }
    }
}