package com.example.addnamesavedata

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var nameEntry: EditText
    private lateinit var addNameButton: Button
    private lateinit var resultTextView: TextView

    // ViewModel to keep names during rotation
    private val namesViewModel: NamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        nameEntry = findViewById(R.id.nameEntry)
        addNameButton = findViewById(R.id.addNameButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Observe the LiveData from ViewModel
        namesViewModel.namesList.observe(this, Observer { names ->
            updateResultsDisplay(names)
        })

        // Set up click listener for the button
        addNameButton.setOnClickListener {
            addName()
        }
    }

    private fun addName() {
        val name = nameEntry.text.toString().trim() // Get text input
        if (name.isNotBlank()) {
            namesViewModel.addName(name) // Add name to ViewModel
            nameEntry.text.clear() // Clear input field
        } else {
            resultTextView.text = getString(R.string.error_message)
        }
    }

    private fun updateResultsDisplay(names: List<String>) {
        // Check if the list is empty and display a message accordingly
        if (names.isEmpty()) {
            resultTextView.text = getString(R.string.name_display)
        } else {
            resultTextView.text =
                names.joinToString("\n") // Display the names if the list is not empty
        }
    }
}
