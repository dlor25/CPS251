package com.example.contactsprojectgiraffe

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsprojectgiraffe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ContactListAdapter.OnContactDeleteListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        adapter = ContactListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

// Observe all contacts
        viewModel.getSortedContacts()?.observe(this) { contacts ->
            contacts?.let { adapter.setContacts(it) } // Safely handling null
        }

// Observe search results
        viewModel.getSearchResults().observe(this) { results ->
            if (results.isEmpty()) {
                Toast.makeText(this, "No results found.", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setContacts(results)
            }
        }

        // Add button click listener
        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val phone = binding.editTextPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                showToast("Please enter both name and phone number")
                return@setOnClickListener
            }

            val contact = Contact(name, phone)
            viewModel.insertContact(contact)

            // Clear input fields
            binding.editTextName.text.clear()
            binding.editTextPhone.text.clear()
            binding.editTextName.requestFocus()
        }

        // Search button click listener
        binding.buttonSearch.setOnClickListener {
            val searchText = binding.editTextName.text.toString().trim()
            if (searchText.isEmpty()) {
                showToast("Please enter a search criteria in the name field")
                return@setOnClickListener
            }

            viewModel.findContact(searchText)

            // Clear input fields
            binding.editTextName.text.clear()
            binding.editTextPhone.text.clear()
        }

        // Sort buttons click listeners
        binding.buttonSortAsc.setOnClickListener {
            viewModel.sortContactsAsc()?.observe(this) { contacts ->
                contacts?.let { adapter.setContacts(it) }
            }
        }

        binding.buttonSortDesc.setOnClickListener {
            viewModel.sortContactsDesc()?.observe(this) { contacts ->
                contacts?.let { adapter.setContacts(it) }
            }
        }
    }

    override fun onDeleteClick(contact: Contact) {
        viewModel.deleteContact(contact.id)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}