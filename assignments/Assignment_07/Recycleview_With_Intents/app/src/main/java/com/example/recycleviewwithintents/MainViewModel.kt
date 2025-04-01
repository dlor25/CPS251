package com.example.recycleviewwithintents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        if (_items.value == null) {  // Only shuffle ONCE
            generateRandomItems()
        }
    }

    private fun generateRandomItems() {
        // Shuffle titles, details, and images independently
        val shuffledTitles = Data.titles.shuffled()
        val shuffledDetails = Data.details.shuffled()
        val shuffledImages = Data.images.shuffled()

        // Create item list by pairing them randomly
        val itemList = shuffledTitles.zip(shuffledDetails)
            .zip(shuffledImages) { (title, detail), image ->
                Item(title, detail, image)
            }

        _items.value = itemList
    }
}
