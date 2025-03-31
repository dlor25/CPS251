package com.example.recycleviewwithintents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewwithintents.databinding.CardViewBinding

class RecyclerAdapter(private val items: List<Item>, private val onItemClick: (Item) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.itemTitle.text = item.title
            binding.itemDetail.text = item.detail
            binding.itemImage.setImageResource(item.imageResId)

            // Handle click event and pass data through intent
            itemView.setOnClickListener {
                onItemClick(item) // Use onItemClick callback to send the item data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

