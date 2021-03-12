package com.capgemini.module_sample.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.ReposPullRequestItem
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_communicate.arouter.RouterExtra
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityPullRequestBinding
import com.capgemini.module_sample.databinding.SimpleActivityPullRequestItemBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicInteger


class SimpleReposPullRequestAdapter :
    ListAdapter<ReposPullRequestItem, SimpleReposPullRequestAdapter.ItemViewHolder>(PullRequestItemDiffCallback()) {

    lateinit var reposPullRequestItem:ReposPullRequestItem
    private val count = AtomicInteger(RouterExtra.ATOMICINTEGER)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val binding:SimpleActivityPullRequestItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.simple_activity_pull_request_item,parent,false
       )
        return ItemViewHolder(binding)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        reposPullRequestItem = getItem(position)
        reposPullRequestItem?.countNumber = count.incrementAndGet()
        holder.bind(reposPullRequestItem!!)
    }
    class ItemViewHolder (val binding:SimpleActivityPullRequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reposPullRequestItem: ReposPullRequestItem) = with(itemView){
            binding.item = reposPullRequestItem
        }
    }
    class PullRequestItemDiffCallback : DiffUtil.ItemCallback<ReposPullRequestItem>() {
        override fun areItemsTheSame(
            oldItem: ReposPullRequestItem,
            newItem: ReposPullRequestItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ReposPullRequestItem,
            newItem: ReposPullRequestItem
        ): Boolean {
            return oldItem == newItem
        }

    }

}