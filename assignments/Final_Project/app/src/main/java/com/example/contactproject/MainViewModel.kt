package com.example.contactproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository
    private val allContacts: LiveData<List<Contact>>?
    private val searchResults: MutableLiveData<List<Contact>>

    init {
        val database = ContactDatabase.getDatabase(application)
        val dao = database.contactDao()
        repository = ContactRepository(dao)  // Passing the ContactDao here, similar to ProductRepository
        allContacts = repository.allContacts
        searchResults = MutableLiveData() // Initializing MutableLiveData instead of LiveData
    }

    fun insertContact(contact: Contact) {
        repository.insert(contact)
    }

    fun findContact(name: String?) {
        val nonNullName = name?.trim() ?: ""
        if (nonNullName.isNotEmpty()) {
            repository.search(nonNullName).observeForever { results ->
                searchResults.postValue(results)
            }
        } else {
            searchResults.postValue(emptyList())
        }
    }

    fun deleteContact(id: Int) {
        repository.delete(id)
    }

    fun getSearchResults(): MutableLiveData<List<Contact>> {
        return searchResults  // MutableLiveData to allow updates
    }

    fun getAllContacts(): LiveData<List<Contact>>? {
        return allContacts
    }

    fun sortContactsAsc(): LiveData<List<Contact>>? {
        return repository.sortAsc()
    }

    fun sortContactsDesc(): LiveData<List<Contact>>? {
        return repository.sortDesc()
    }
}
