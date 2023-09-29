package com.lessonit.bmi_calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.lessonit.bmi_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.calBtn.setOnClickListener{
            val weight = binding.weightEtext.text.toString()
            val height = binding.hightEtext.text.toString()
            if (validateInput(weight, height)){
                val bmi = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))
                val bmiDigit = String.format("%.2f",bmi).toDouble()
                displayResult(bmiDigit)
            }
        }
    }

    private fun displayResult(bmi:Double) {
        binding.countText.text = bmi.toString()
        binding.resulttext.text = "You are Healthy"
        binding.rangetxt.text = "(Normal range is 18.5-25.5)"

        var result = ""
        var color = 0
        var range = ""

        when{
            bmi < 18.5 ->{
                result = "Underweight"
                color = R.color.undert_weight
                range = "(Underweight range is less than 18.50)"
            }
            bmi in 18.5..24.99 ->{
                result = "Healthy"
                color = R.color.normal
                range = "(Healthy range is 18.50 to 24.99)"
            }
            bmi in 25.00..29.99 ->{
                result = "Overweight"
                color = R.color.over_weight
                range = "(Overweight range is 25.00 to 29.99)"
            }
            bmi > 29.99->{
                result = "Obese"
                color = R.color.obese
                range = "(Obese range is greter than 29.99)"
            }
        }
        binding.resulttext.text = result
        binding.resulttext.setTextColor(ContextCompat.getColor(this, color))
        binding.rangetxt.text = range
        binding.rangetxt.setTextColor(ContextCompat.getColor(this, color))

    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
}