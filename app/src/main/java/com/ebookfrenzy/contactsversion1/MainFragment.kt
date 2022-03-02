package com.ebookfrenzy.contactsversion1

import android.os.Bundle
import androidx.fragment.app.viewModels //this is a part of the fragment dependency library implemented in the gradle build file
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.contactsversion1.databinding.MainFragmentBinding
import java.util.*
import com.ebookfrenzy.contactsversion1.R
import com.ebookfrenzy.contactsversion1.Contacts
import androidx.lifecycle.Observer

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: ContactListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.firstName.setText("")
        binding.lastName.setText("")
        binding.phoneNumber.setText("")
    }

    private fun clearFieldsAfterNoMatch() {
        binding.firstName.setText("No Match!!")
        binding.lastName.setText("")
        binding.phoneNumber.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val firstNme = binding.firstName.text.toString()
            val lastNme = binding.lastName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            if (firstNme != "" && lastNme != "" && phoneNumber !="") {
                val contact = Contacts(phoneNumber.toInt(), firstNme, lastNme)
                viewModel.insertContact(contact)
                clearFields()
            } else {
                binding.firstName.setText("Incomplete Information")
                binding.firstName.setText("Incomplete Information")
            }
        }
        binding.findButton.setOnClickListener { viewModel.findContact(
            Integer.parseInt(binding.phoneNumber.text.toString()))}

        binding.deleteButton.setOnClickListener {
            viewModel.deleteContact( Integer.parseInt(binding.phoneNumber.text.toString()))
            clearFields()
        }
    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        })
        viewModel.getSearchResults().observe(viewLifecycleOwner, Observer { products ->
            products?.let {
                if (it.isNotEmpty()) {
                    binding.firstName.setText( it[0].contactFirstName)
                    binding.lastName.setText(it[0].contactLastName)
                    binding.phoneNumber.setText(String.format(
                        Locale.US, "%d",
                        it[0].phoneNumberId))
                } else {
                    clearFieldsAfterNoMatch()
                }
            }
        })
    }


    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.contact_details)
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }





}