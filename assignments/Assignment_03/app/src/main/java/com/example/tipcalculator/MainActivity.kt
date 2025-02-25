package com.example.tipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAmount = findViewById(R.id.etAmount)
        resultTextView = findViewById(R.id.resultTextView)
        val calculateButton: Button = findViewById(R.id.calculateButton)

        // Setting up the Button click listener
        calculateButton.setOnClickListener {
            calculateTips()
        }
    }

    private fun calculateTips() {
        val billAmountText = etAmount.text.toString()

        if (billAmountText.isNotEmpty()) {
            val billAmount = billAmountText.toDouble()

            // Calculate the tips for 10%, 20%, and 30%
            val tip10 = billAmount * 1.10
            val tip15 = billAmount * 1.15
            val tip20 = billAmount * 1.20

            // Display the results
            val result = getString(R.string.tip_header) + "\n\n" +
                getString(R.string.tip_10, tip10) + "\n" +
                getString(R.string.tip_15, tip15) + "\n" +
                getString(R.string.tip_20, tip20)

            resultTextView.text = result
        } else {
            resultTextView.text = getString(R.string.error_message)
        }
    }
}
