package com.ebookfrenzy.contactsversion1

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import com.ebookfrenzy.contactsversion1.Contacts
import com.ebookfrenzy.contactsversion1.R

class ContactListAdapter(private val contactItemLayout: Int): RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var contactsList: List<Contacts>? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.contactNme
        val item2 = holder.contactNum

        contactsList.let {
            item.text = it!![position].contactFirstName + " " + it!![position].contactLastName
            item2.text = "0" + it!![position].phoneNumberId.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.contact_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (contactsList == null) 0 else contactsList!!.size
    }

    fun setContactList(contacts: List<Contacts>?) {
        contactsList = contacts
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contactNme: TextView = itemView.findViewById(R.id.contactName)
        var contactNum: TextView = itemView.findViewById(R.id.contactDetails)
    }
}