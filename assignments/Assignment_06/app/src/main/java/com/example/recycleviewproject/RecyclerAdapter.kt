package com.example.recycleviewproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewproject.databinding.CardViewBinding
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter(private val items: List<Item>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { v ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    Snackbar.make(v, "Clicked: ${items[position].title}", Snackbar.LENGTH_LONG)
                        .setAction("OK", null)
                        .show()
                }
            }
        }

        fun bind(item: Item) {
            binding.itemImage.setImageResource(item.imageResId)
            binding.itemTitle.text = item.title
            binding.itemDetail.text = item.detail
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
