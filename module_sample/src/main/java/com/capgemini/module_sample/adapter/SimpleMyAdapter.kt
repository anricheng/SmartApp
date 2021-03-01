package com.capgemini.module_sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.ItemEntity
import com.capgemini.entity.UserInformation
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.ListFragment

class SimpleMyAdapter(private val userList:List<UserInformation>):RecyclerView.Adapter<SimpleMyAdapter.SimpleMyViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleMyAdapter.SimpleMyViewHolder {
        val binding:ListFragment = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.simple_listfragment_list,parent,false)
        return SimpleMyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: SimpleMyAdapter.SimpleMyViewHolder, position: Int) {

        var user:UserInformation = userList.get(position)
        if (user!=null){
            holder.bind(user)
        }

    }


    class SimpleMyViewHolder(val binding: ListFragment)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(userInformation: UserInformation) = with(itemView) {
                binding.user = userInformation
            }
        }

}