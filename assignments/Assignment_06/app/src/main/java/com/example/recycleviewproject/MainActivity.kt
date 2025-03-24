package com.example.recycleviewproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.mRecycleView.layoutManager = LinearLayoutManager(this)

        // Get random items
        val randomData = Data().getRandomItems(8)

        // Initialize Adapter
        adapter = RecyclerAdapter(randomData)
        binding.mRecycleView.adapter = adapter
    }
}
