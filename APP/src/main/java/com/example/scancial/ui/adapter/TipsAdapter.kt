package com.example.scancial.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scancial.core.entity.Tips
import com.example.scancial.databinding.BannerHomeBinding

class TipsAdapter : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {
    private val tips = ArrayList<Tips>()

    fun setData(items: List<Tips>) {
        tips.clear()
        tips.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BannerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tips[position])
    }

    override fun getItemCount(): Int {
        return tips.size
    }

    inner class ViewHolder(private val binding: BannerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(item: Tips) {
            with(binding) {
                tv1.text = item.name
            }
        }
    }
}