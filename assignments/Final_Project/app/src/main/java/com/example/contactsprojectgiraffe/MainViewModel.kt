package com.example.contactsprojectgiraffe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository
    private val _displayedContacts = MutableLiveData<List<Contact>>()
    val displayedContacts: LiveData<List<Contact>> = _displayedContacts

    enum class LastAction { NONE, SEARCH, SORT_ASC, SORT_DESC }

    private var lastAction = LastAction.NONE
    private var lastQuery: String? = null
    private var previousContacts: List<Contact> = emptyList() // Store previous contacts

    private val _searchFailed = MutableLiveData<Boolean>()
    val searchFailed: LiveData<Boolean> = _searchFailed

    init {
        val dao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(dao)

        // Load all contacts initially, using lifecycle-aware observation
        repository.allContacts.observeForever {
            if (lastAction == LastAction.NONE) {
                previousContacts = it // Store the initial list
                _displayedContacts.postValue(it)
            }
        }
    }

    fun insertContact(contact: Contact) {
        repository.insert(contact)
        refreshLastAction()
    }

    fun deleteContact(id: Int) {
        repository.delete(id)
        refreshLastAction()
    }

    fun findContact(name: String?) {
        val query = name?.trim() ?: ""
        if (query.isNotEmpty()) {
            lastQuery = query
            lastAction = LastAction.SEARCH
            // Using live data and observing the search result lifecycle-aware
            repository.search(query).observeForever { results ->
                if (results.isEmpty()) {
                    _searchFailed.postValue(true) // Set flag that search failed
                    _displayedContacts.postValue(previousContacts) // Restore previous contacts
                } else {
                    _searchFailed.postValue(false)
                    _displayedContacts.postValue(results) // Show search results
                }
            }
        }
    }

    fun sortContactsAsc() {
        lastAction = LastAction.SORT_ASC
        repository.sortAsc()?.observeForever {
            _displayedContacts.postValue(it)
        }
    }

    fun sortContactsDesc() {
        lastAction = LastAction.SORT_DESC
        repository.sortDesc()?.observeForever {
            _displayedContacts.postValue(it)
        }
    }

    fun refreshLastAction() {
        when (lastAction) {
            LastAction.SEARCH -> lastQuery?.let { findContact(it) }
            LastAction.SORT_ASC -> sortContactsAsc()
            LastAction.SORT_DESC -> sortContactsDesc()
            LastAction.NONE -> {
                repository.allContacts.observeForever {
                    previousContacts = it // Store the list when no action is set
                    _displayedContacts.postValue(it)
                }
            }
        }
    }
}
