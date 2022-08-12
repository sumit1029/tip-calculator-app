package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    fun calculateTip() {
        val stringData = binding.costOfService.text.toString()
        val cost = stringData.toDouble()

        val tipPercent = when(binding.tip.checkedRadioButtonId){
            R.id.amazing -> 20.00
            R.id.good -> 18.00
            else -> 15.00
        }

        var tip = (tipPercent/100.00)*cost
        binding.yesNo.isChecked

        val num = 1
        if(binding.yesNo.isChecked){
            tip = ceil(tip)
        }

        binding.result.text = tip.toString()
    }
}