package com.capgemini.module_sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.ItemData
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleItemLayoutTestBinding

class SimpleListAdapter : ListAdapter<ItemData, SimpleListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding: SimpleItemLayoutTestBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.simple_item_layout_test,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleListAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(val binding: SimpleItemLayoutTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemData) = with(itemView) {
            binding.item = item
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ItemData>() {
    override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem.content ==newItem.content
    }

    override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem == newItem
    }
}