package com.example.delotengsmarthidro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.delotengsmarthidro.data.database.TutorialEntity
import com.example.delotengsmarthidro.data.response.ListItem
import com.example.delotengsmarthidro.databinding.ItemTutorialBinding

class TutorListAdapter(
    private val onItemClick: (TutorialEntity) -> Unit
) :  ListAdapter<TutorialEntity, TutorListAdapter.StepViewHolder>(DIFF_CALLBACK) {

    inner class StepViewHolder(private val binding: ItemTutorialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(step: TutorialEntity) {
            binding.apply {
                tvStepTitle.text = step.title
                tvStepDescription.text = step.desc
                step.icon?.let { ivIcon.setImageResource(it) }

                itemView.setOnClickListener{
                    onItemClick(step)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val binding = ItemTutorialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TutorialEntity>() {
            override fun areItemsTheSame(
                oldItem: TutorialEntity,
                newItem: TutorialEntity
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: TutorialEntity,
                newItem: TutorialEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}