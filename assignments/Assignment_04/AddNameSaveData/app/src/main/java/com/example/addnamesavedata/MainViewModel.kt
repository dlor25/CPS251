package com.example.addnamesavedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class NamesViewModel : ViewModel() {
    // LiveData to hold the list of names
    val namesList = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }

    // Add a name to the list
    fun addName(name: String) {
        namesList.value?.add(name)
        namesList.value = namesList.value // trigger update
    }
}
