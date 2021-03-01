package com.capgemini.module_sample.activity

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.UserInformation
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarRightTitle
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_common.extendtions.throttleFirstClick
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LIST
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_USER_LIST
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleListAdapter
import com.capgemini.module_sample.adapter.SimpleMyAdapter
import com.capgemini.module_sample.databinding.MyDataBinding
import com.capgemini.module_sample.databinding.SimpleActivityListBinding
import com.capgemini.module_sample.viewmodel.SimpleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*

@AndroidEntryPoint
@Route(path = SAMPLE_USER_LIST)
class SampleUserListActivity : AppCompatActivity() {
    private val mAdapter = SimpleMyAdapter(getUserImformation())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_activity_user_list)
        initUI()
    }

    private fun initUI(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SampleUserListActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

    fun getUserImformation() : List<UserInformation> {
        ARouter.getInstance().inject(this)
        val bundle: Bundle? = intent.getBundleExtra("bundle")
        val list:List<UserInformation> = bundle?.get("abc") as ArrayList<UserInformation>
        Log.v("list",list.toString())
        return list}

}