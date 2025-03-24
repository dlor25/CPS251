package com.example.recycleviewproject

class Data {
    private val titles = arrayOf("Chapter One",
        "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven",
        "Chapter Eight")

    private val details = arrayOf("Item one details", "Item two details",
        "Item three details", "Item four details",
        "Item five details", "Item six details",
        "Item seven details", "Item eight details")

    private val images = intArrayOf(R.drawable.android_image_1,
        R.drawable.android_image_2, R.drawable.android_image_3,
        R.drawable.android_image_4, R.drawable.android_image_5,
        R.drawable.android_image_6, R.drawable.android_image_7,
        R.drawable.android_image_8)

    // Function to generate a randomized list of items
    fun getRandomItems(count: Int): List<Item> {
        val itemList = mutableListOf<Item>()

        // Randomize indices for each array
        val indices1 = (titles.indices).shuffled().take(count)
        val indices2 = (details.indices).shuffled().take(count)
        val indices3 = (images.indices).shuffled().take(count)

        // Loop through the randomized indices and create Item objects
        for (i in 0 until count) {
            itemList.add(
                Item(
                    titles[indices1[i]],
                    details[indices2[i]],
                    images[indices3[i]]
                )
            )
        }
        return itemList
    }
}

// Data class to hold each item
data class Item(val title: String, val detail: String, val imageResId: Int)
