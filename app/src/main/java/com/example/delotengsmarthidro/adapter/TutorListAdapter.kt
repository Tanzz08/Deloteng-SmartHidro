package com.example.delotengsmarthidro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delotengsmarthidro.data.list.tutorial.TutorList
import com.example.delotengsmarthidro.databinding.ItemTutorialBinding

class TutorListAdapter(private val listSteps: List<TutorList>) : RecyclerView.Adapter<TutorListAdapter.StepViewHolder>() {

    inner class StepViewHolder(private val binding: ItemTutorialBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(step: TutorList) {
            binding.apply {
                tvStepTitle.text = step.title
                tvStepDescription.text = step.desc
                ivIcon.setImageResource(step.icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val binding = ItemTutorialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  StepViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSteps.size
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = listSteps[position]
        holder.bind(step)
    }
}