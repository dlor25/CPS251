package com.example.recycleviewwithintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewwithintents.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get shuffled items from Data
        val itemList = Data.getShuffledItems()

        // Set up RecyclerView with the adapter and click handling
        val adapter = RecyclerAdapter(itemList) { selectedItem ->
            // Create an intent to send data to DetailActivity
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("title", selectedItem.title)
                putExtra("detail", selectedItem.detail)
                putExtra("imageResId", selectedItem.imageResId)
            }
            startActivity(intent)
        }

        binding.mRecycleView.layoutManager = LinearLayoutManager(this)
        binding.mRecycleView.adapter = adapter
    }
}