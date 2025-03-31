package com.example.recycleviewwithintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleviewwithintents.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data passed from MainActivity
        val title = intent.getStringExtra("title") ?: "No Title"
        val detail = intent.getStringExtra("detail") ?: "No Details"
        val imageResId = intent.getIntExtra("imageResId", 0)

        // Set data to views
        binding.detailTitle.text = title
        binding.detailText.text = detail
        binding.detailImage.setImageResource(imageResId)
    }
}

