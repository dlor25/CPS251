package com.example.coroutinesproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val _cardList = MutableLiveData<List<String>>(emptyList())
    val cardList: LiveData<List<String>> = _cardList

    fun addCard(name: String) {
        viewModelScope.launch {
            val delayTime = (1..10).random() * 1000L
            delay(delayTime)

            val message = "The name is $name and the delay was $delayTime milliseconds"

            val updatedList = _cardList.value.orEmpty().toMutableList().apply {
                add(message)
            }
            _cardList.postValue(updatedList)
        }
    }
}
