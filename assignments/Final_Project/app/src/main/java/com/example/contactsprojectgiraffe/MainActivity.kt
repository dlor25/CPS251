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
    private var isSearchButtonClicked = false // Flag to track search button click

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView
        adapter = ContactListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Observe ONLY displayedContacts
        viewModel.displayedContacts.observe(this) { contacts ->
            // Show the contacts
            adapter.setContacts(contacts)

            // If the search button was clicked and no results were found, show the Toast
            viewModel.searchFailed.observe(this) { failed ->
                if (failed && isSearchButtonClicked) {
                    showToast("No results found.")
                    isSearchButtonClicked = false
                }
            }
        }

        // Add
        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val phone = binding.editTextPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                showToast("Please enter both name and phone number")
            } else {
                viewModel.insertContact(Contact(name, phone))
                binding.editTextName.text.clear()
                binding.editTextPhone.text.clear()
                binding.editTextName.requestFocus()
            }
        }

        // Search
        binding.buttonSearch.setOnClickListener {
            val query = binding.editTextName.text.toString().trim()
            if (query.isEmpty()) {
                showToast("Please enter a search criteria in the name field")
            } else {
                isSearchButtonClicked = true
                viewModel.findContact(query)
                binding.editTextName.text.clear()
                binding.editTextPhone.text.clear()
            }
        }

        // Sort
        binding.buttonSortAsc.setOnClickListener {
            viewModel.sortContactsAsc()
        }

        binding.buttonSortDesc.setOnClickListener {
            viewModel.sortContactsDesc()
        }

        // Refresh state after rotation
        viewModel.refreshLastAction()
    }

    override fun onDeleteClick(contact: Contact) {
        viewModel.deleteContact(contact.id)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
