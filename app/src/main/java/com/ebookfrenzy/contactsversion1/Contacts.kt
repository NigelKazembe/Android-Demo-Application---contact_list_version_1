package com.ebookfrenzy.contactsversion1

import androidx.annotation.NonNull
import androidx.room.Insert
import  androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "contacts")
class Contacts {
    @ColumnInfo(name="firstName")
    var contactFirstName: String? = null

    @ColumnInfo(name="lastName")
    var contactLastName: String? = null

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "phoneNumber")
    var phoneNumberId: Int? = 0

    constructor() {}

    constructor(phoneNumber: Int, firstName: String, lastName: String) {
        phoneNumberId = phoneNumber
        contactFirstName = firstName
        contactLastName = lastName
    }

    constructor(firstName: String, lastName: String) {
        contactFirstName = firstName
        contactLastName = lastName
    }


}