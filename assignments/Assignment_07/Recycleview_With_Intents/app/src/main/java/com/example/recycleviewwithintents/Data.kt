package com.example.recycleviewwithintents

// Data class to represent each item
data class Item(
    val title: String,
    val detail: String,
    val imageResId: Int
)

object Data {
    private val titles = listOf(
        "Chapter One", "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight"
    )

    private val details = listOf(
        "Item one details", "Item two details", "Item three details",
        "Item four details", "Item five details", "Item six details",
        "Item seven details", "Item eight details"
    )

    private val images = listOf(
        R.drawable.android_image_1, R.drawable.android_image_2, R.drawable.android_image_3,
        R.drawable.android_image_4, R.drawable.android_image_5, R.drawable.android_image_6,
        R.drawable.android_image_7, R.drawable.android_image_8
    )

    // Function to return shuffled items
    fun getShuffledItems(): List<Item> {
        // Shuffle titles, details, and images independently
        val shuffledTitles = titles.shuffled()
        val shuffledDetails = details.shuffled()
        val shuffledImages = images.shuffled()

        // Create item list by pairing them randomly
        return shuffledTitles.zip(shuffledDetails)
            .zip(shuffledImages) { (title, detail), image ->
                Item(title, detail, image)
            }
    }
}
