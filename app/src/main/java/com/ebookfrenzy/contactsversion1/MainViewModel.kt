package com.ebookfrenzy.contactsversion1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebookfrenzy.contactsversion1.Contacts
import com.ebookfrenzy.contactsversion1.ContactsRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryInstance: ContactsRepository = ContactsRepository(application)
    private val allContactsReturned: LiveData<List<Contacts>>?
    private val searchResultsReturned: MutableLiveData<List<Contacts>>

    init {
        allContactsReturned = repositoryInstance.allContacts
        searchResultsReturned = repositoryInstance.searchResults
    }
    //Function/method to be called by a button to insert a contact into the database
    fun insertContact(contact: Contacts) {
        repositoryInstance.insertContacts(contact)
    }

    //Function/method to be called by a button to delete a contact from the database
    fun deleteContact(phoneNum: Int) {
        repositoryInstance.deleteContact(phoneNum)
    }

    //Function/method to be called by a button to find a contact in the database
    fun findContact(phoneNum: Int) {
        repositoryInstance.getContactWithPhoneNumber(phoneNum)
    }

    //Function/method to be return all contacts in the database for monitoring by the observer and updating the recycler view
    fun getAllContacts(): LiveData<List<Contacts>>? {
        return allContactsReturned
    }

    //Function/method to be called to return the search results from the database
    fun getSearchResults(): MutableLiveData<List<Contacts>> {
        return searchResultsReturned
    }
}