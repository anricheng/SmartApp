package com.capgemini.module_sample.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.UserInformation
import com.capgemini.lib_common.base.BaseActivity
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
class SampleUserListActivity : BaseActivity(){
    @JvmField
    @Autowired(name = "abc")
    var list:List<UserInformation>? = null
    private val mAdapter = list?.let { SimpleMyAdapter(it) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_activity_user_list)
        Log.v("list",list.toString())
        initUI()
    }

    override fun getLayoutId(): Int {
        return 0
    }

    private fun initUI(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SampleUserListActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }
}