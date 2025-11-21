package com.example.delotengsmarthidro.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.delotengsmarthidro.data.database.HistoryEntity
import com.example.delotengsmarthidro.databinding.ItemHistoryBinding

class HistoryAdapter(private val onItemClick: (HistoryEntity) -> Unit) :
    ListAdapter<HistoryEntity, HistoryAdapter.HistoryViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(history: HistoryEntity) {
            binding.apply {
                tvLabel.text = history.label
                tvDesc.text = history.ciriCiri

                Glide.with(itemView.context)
                    .load(history.imageUri)
                    .into(binding.ivHistory)

            }

            itemView.setOnClickListener {
                onItemClick(history)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<HistoryEntity>() {
            override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity) =
                oldItem == newItem
        }
    }
}
