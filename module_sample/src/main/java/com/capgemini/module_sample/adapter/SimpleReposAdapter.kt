package com.capgemini.module_sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.databinding.onClick
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleItemLayoutRepositoryBinding


class SimpleReposAdapter : ListAdapter<RepositoriesItem, SimpleReposAdapter.ItemViewHolder>(
    RepositoryItemDiffCallback()
) {
    private var msetOnClickListemer: setOnClickListener? = null
    private var repositoriesItem:RepositoriesItem? = null
    fun setItemClickListener(s1: setOnClickListener) {
        msetOnClickListemer = s1
    }
//    fun setItemClickListener() {
//        NavigationHelper.navigation(SampleModuleARouterPath.SAMPLE_REPOS_DETAILS)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: SimpleItemLayoutRepositoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.simple_item_layout_repository,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        repositoriesItem = getItem(position)
        holder.bind(repositoriesItem!!)
        holder.itemView.setOnClickListener({ v ->
            msetOnClickListemer?.Onclick(repositoriesItem!!)
        })
    }

    class ItemViewHolder(val binding: SimpleItemLayoutRepositoryBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: RepositoriesItem) = with(itemView) {
            binding.item = item
        }
    }

    class RepositoryItemDiffCallback : DiffUtil.ItemCallback<RepositoriesItem>() {
        override fun areItemsTheSame(
            oldItem: RepositoriesItem,
            newItem: RepositoriesItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: RepositoriesItem,
            newItem: RepositoriesItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}