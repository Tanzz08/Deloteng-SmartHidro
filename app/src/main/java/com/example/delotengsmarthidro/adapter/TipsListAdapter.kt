package com.example.delotengsmarthidro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delotengsmarthidro.data.list.tips.TipsList
import com.example.delotengsmarthidro.data.list.tutorial.TutorList
import com.example.delotengsmarthidro.databinding.ItemTipsBinding
import com.example.delotengsmarthidro.databinding.ItemTutorialBinding

class TipsListAdapter(private val listTips: List<TipsList>) : RecyclerView.Adapter<TipsListAdapter.TipsViewHolder>() {

    inner class TipsViewHolder(private val binding: ItemTipsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(step: TipsList) {
            binding.apply {
                tvStepTitle.text = step.title
                tvStepDescription.text = step.desc
                ivIcon.setImageResource(step.icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder {
        val binding = ItemTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  TipsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTips.size
    }

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) {
        val tips = listTips[position]
        holder.bind(tips)
    }
}