package com.ebookfrenzy.contactsversion1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import com.ebookfrenzy.contactsversion1.Contacts

@Dao
interface ContactsDao {
    @Insert
    fun insertContact(contact: Contacts)

    @Query("SELECT * FROM contacts WHERE phoneNumber =:number")
    fun findContactWithNumber(number: Int): List<Contacts>?

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contacts>>?

    @Query("DELETE FROM contacts WHERE phoneNumber = :number")
    fun deleteContact(number: Int)
}