package com.capgemini.module_sample.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.entity.UserInformation
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleListfragmentListBinding

class SimpleMyAdapter(private val userList:List<UserInformation>):RecyclerView.Adapter<SimpleMyAdapter.SimpleMyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleMyViewHolder {
        var view:View = View.inflate(parent.context,R.layout.simple_activity_user_list,null)
        val binding: SimpleListfragmentListBinding =DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.simple_listfragment_list,
                parent,
                false
        )
        return SimpleMyViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(holder: SimpleMyViewHolder, position: Int) {
        var user: UserInformation = userList.get(position)
        if (user != null) {
            holder.bind(user)
        }

    }
    class SimpleMyViewHolder(val binding: SimpleListfragmentListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userInformation: UserInformation) {
            binding.user = userInformation
        }
    }

}