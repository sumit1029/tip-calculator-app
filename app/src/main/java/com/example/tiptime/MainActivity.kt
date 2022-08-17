package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun calculateTip() {
        val stringData = binding.costOfServiceEditText.text.toString()
        if(stringData.isEmpty()){
            binding.costOfService.hint = "Please Enter Cost"
            val tip = 0
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.result.text = getString(R.string.tip_amount, formattedTip)
            return
        }
        val cost = stringData.toDouble()

        val tipPercent = when(binding.tip.checkedRadioButtonId){
            R.id.amazing -> 20.00
            R.id.good -> 18.00
            else -> 15.00
        }

        var tip = (tipPercent/100.00)*cost
        binding.yesNo.isChecked

        if(binding.yesNo.isChecked){
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.result.text = getString(R.string.tip_amount, formattedTip)

    }
}