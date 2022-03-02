package com.ebookfrenzy.contactsversion1

import com.ebookfrenzy.contactsversion1.ContactsDao
import com.ebookfrenzy.contactsversion1.ContactsDatabase
import com.ebookfrenzy.contactsversion1.Contacts

import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactsRepository (application: Application){
    val searchResults = MutableLiveData<List<Contacts>>()
    private var contactDao: ContactsDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contacts>>?

    init{
        val roomDBInstance: ContactsDatabase? = ContactsDatabase.getDatabase(application)
        contactDao = roomDBInstance?.contactsDao()
        allContacts = contactDao?.getAllContacts()
    }

    fun insertContacts(contact: Contacts) {
        coroutineScope.launch(Dispatchers.IO) {
            insert(contact)
        }
    }

    private suspend fun insert(contact: Contacts){
        contactDao?.insertContact(contact)
    }

    fun deleteContact(phoneNumber: Int) {
        coroutineScope.launch(Dispatchers.IO){
            delete(phoneNumber)
        }
    }

    private suspend fun delete(phoneNumber: Int) {
        contactDao?.deleteContact(phoneNumber)
    }

    fun getContactWithPhoneNumber(phoneNumber: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            searchResults?.postValue(findContact(phoneNumber).await())
        }
    }

    private suspend fun findContact(phoneNumber: Int): Deferred<List<Contacts>?> = coroutineScope.async(Dispatchers.IO) {
        return@async contactDao?.findContactWithNumber(phoneNumber)
    }
}