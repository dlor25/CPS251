package com.example.contactproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository(contactDao: ContactDao) {
    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao? = contactDao // Assign the provided ContactDao
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    fun insert(newContact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            contactDao?.insertContact(newContact)
        }
    }

    fun delete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            contactDao?.deleteContact(id)
        }
    }

    fun search(name: String): LiveData<List<Contact>> {
        return contactDao?.searchContacts(name) ?: MutableLiveData(emptyList())
    }


    fun sortAsc(): LiveData<List<Contact>>? {
        return contactDao?.getAllContactsSortedAsc()
    }

    fun sortDesc(): LiveData<List<Contact>>? {
        return contactDao?.getAllContactsSortedDesc()
    }
}
