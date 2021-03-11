package com.capgemini.module_sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.RepositoriesItem
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleItemLayoutRepositoryBinding

class SimpleReposAdapter2: RecyclerView.Adapter<SimpleReposAdapter2.ItemViewHolder>() {
    var repositoriesItem:RepositoriesItem? = null

    class ItemViewHolder(val binding:SimpleItemLayoutRepositoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:RepositoriesItem) = with(itemView){
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding:SimpleItemLayoutRepositoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.simple_item_layout_repository,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        repositoriesItem = getItemViewType(position)
            holder.bind(repositoriesItem!!)
    }

    override fun getItemCount(): Int {
        return itemCount
    }
}