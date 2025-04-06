package com.example.coroutinesproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinesproject.databinding.CardViewBinding

class RecyclerAdapter : ListAdapter<String, RecyclerAdapter.CardViewHolder>(DiffCallback()) {

    class CardViewHolder(private val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.nameTextView.text = name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

