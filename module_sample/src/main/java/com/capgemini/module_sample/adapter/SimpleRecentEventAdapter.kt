package com.capgemini.module_sample.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.RecentEvent
import com.capgemini.entity.RepositoriesItem
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleItemLayoutRecentEventBinding

class SimpleRecentEventAdapter : ListAdapter<RecentEvent, SimpleRecentEventAdapter.ItemViewHolder>(RecentEventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding: SimpleItemLayoutRecentEventBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.simple_item_layout_recent_event,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * 创建条目view
     */
    class ItemViewHolder(val binding: SimpleItemLayoutRecentEventBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(item: RecentEvent) = with(itemView) {
                binding.item = item
            }
    }

}

class RecentEventDiffCallback : DiffUtil.ItemCallback<RecentEvent>() {
    override fun areItemsTheSame(oldItem: RecentEvent, newItem: RecentEvent): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: RecentEvent, newItem: RecentEvent): Boolean {
        return oldItem == newItem
    }
}
