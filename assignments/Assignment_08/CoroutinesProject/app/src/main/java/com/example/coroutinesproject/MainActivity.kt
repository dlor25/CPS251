package com.example.coroutinesproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var cardViewModel: MainViewModel
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.nameEditText)
        val addButton = findViewById<Button>(R.id.addNameButton)
        val recyclerView = findViewById<RecyclerView>(R.id.cardRecyclerView)

        // Setup RecyclerView
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Setup ViewModel
        cardViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Observe card list
        cardViewModel.cardList.observe(this) { cards ->
            adapter.submitList(cards)
        }

        // Add name on button click
        addButton.setOnClickListener {
            val name = editText.text.toString().trim()
            if (name.isNotEmpty()) {
                cardViewModel.addCard(name)
                editText.text.clear()
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


