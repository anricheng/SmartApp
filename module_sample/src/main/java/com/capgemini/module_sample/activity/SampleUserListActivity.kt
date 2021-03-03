package com.capgemini.module_sample.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.UserInformation
import com.capgemini.lib_common.base.BaseActivity
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarRightTitle
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_common.extendtions.replaceFragment
import com.capgemini.lib_common.extendtions.throttleFirstClick
import com.capgemini.lib_communicate.arouter.RouterExtra
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_USER_LIST
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleMyAdapter
import com.capgemini.module_sample.databinding.SimpleActivityListBinding
import com.capgemini.module_sample.databinding.SimpleActivityUserListBinding
import com.capgemini.module_sample.viewmodel.SimpleListViewModel
import com.capgemini.module_sample.viewmodel.SimpleMainActivityViewModel
import com.capgemini.module_sample.viewmodel.SimpleUserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*

@AndroidEntryPoint
@Route(path = SAMPLE_USER_LIST)
class SampleUserListActivity : BaseDataBindingActivity<SimpleActivityUserListBinding>() {
    @JvmField
    @Autowired(name = RouterExtra.USERIMFORMATION)
    var list:List<UserInformation>? = null

    private val viewModel by viewModels<SimpleUserListViewModel>()
    private val mAdapter = list?.let { SimpleMyAdapter(it) }

    override fun setupViewModel() {
        binding.viewmodel = viewModel
    }

    override fun setupView() {
       list = intent.extras?.getParcelableArrayList<UserInformation>(RouterExtra.USERIMFORMATION)
        mToolbarTitle.text = "这是列表页面"
        Log.v("list",list.toString())
        initUI()
        mToolbarRightTitle.text = "添加数据"
    }

    override fun setupListener() {
        mToolbarRightTitle.throttleFirstClick{
            viewModel.createUserImformation()
        }
    }

    override fun getLayoutId(): Int = R.layout.simple_activity_user_list

    private fun initUI(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SampleUserListActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

    }
}