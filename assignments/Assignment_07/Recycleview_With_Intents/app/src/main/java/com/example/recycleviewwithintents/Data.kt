package com.example.recycleviewwithintents

object Data {
    val titles = listOf(
        "Chapter One", "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight"
    )

    val details = listOf(
        "Item one details", "Item two details", "Item three details",
        "Item four details", "Item five details", "Item six details",
        "Item seven details", "Item eight details"
    )

    val images = listOf(
        R.drawable.android_image_1, R.drawable.android_image_2, R.drawable.android_image_3,
        R.drawable.android_image_4, R.drawable.android_image_5, R.drawable.android_image_6,
        R.drawable.android_image_7, R.drawable.android_image_8
    )
}

// ✅ Move Item outside of Data
data class Item(val title: String, val detail: String, val imageResId: Int)
