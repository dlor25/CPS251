package com.example.contactsprojectgiraffe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact(
    // Change to non-nullable String
    @ColumnInfo(name = "contactName") var contactName: String,
    // Change to non-nullable String
    @ColumnInfo(name = "contactPhone") var contactPhone: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contactId")
    var id: Int = 0

}
