package com.ebookfrenzy.contactsversion1

import androidx.room.Database
import com.ebookfrenzy.contactsversion1.Contacts
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [(Contacts::class)], version = 1)
abstract class ContactsDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactsDao

    companion object {
        private var INSTANCE: ContactsDatabase? = null
        internal fun getDatabase(context: Context): ContactsDatabase? {
            if (INSTANCE == null) {
                synchronized(ContactsDatabase::class.java){
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder<ContactsDatabase>(
                                context.applicationContext, ContactsDatabase::class.java,
                                "contacts_database" ).build()
                    }
                }
            }
            return INSTANCE
        }
    }

}