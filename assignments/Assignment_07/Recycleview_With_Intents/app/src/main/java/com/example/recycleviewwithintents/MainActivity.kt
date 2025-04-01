package com.example.recycleviewwithintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewwithintents.databinding.ActivityMainBinding
import android.content.Intent
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels() // Use ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe LiveData to get the shuffled list
        viewModel.items.observe(this) { itemList ->
            setupRecyclerView(itemList)
        }
    }

    private fun setupRecyclerView(itemList: List<Item>) {  // ✅ FIXED
        val adapter = RecyclerAdapter(itemList) { selectedItem ->
            openDetailActivity(selectedItem)
        }

        binding.mRecycleView.layoutManager = LinearLayoutManager(this)
        binding.mRecycleView.adapter = adapter
    }

    private fun openDetailActivity(item: Item) {  // ✅ FIXED
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("imageResId", item.imageResId)
            putExtra("title", item.title)
            putExtra("detail", item.detail)
        }
        startActivity(intent)
    }
}
