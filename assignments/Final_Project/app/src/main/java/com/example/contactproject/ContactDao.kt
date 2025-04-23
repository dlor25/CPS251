package com.example.contactproject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    fun searchContacts(name: String): LiveData<List<Contact>>

    @Query("DELETE FROM contacts WHERE contactId = :id")
    fun deleteContact(id: Int)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    fun getAllContactsSortedAsc(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    fun getAllContactsSortedDesc(): LiveData<List<Contact>>
}
