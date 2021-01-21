package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarRightTitle
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_common.extendtions.throttleFirstClick
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LIST
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleListAdapter
import com.capgemini.module_sample.databinding.SimpleActivityListBinding
import com.capgemini.module_sample.viewmodel.SimpleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*

@AndroidEntryPoint
@Route(path = SAMPLE_LIST)
class SimpleListActivity : BaseDataBindingActivity<SimpleActivityListBinding>() {
    private val viewModel by viewModels<SimpleListViewModel>()

    private val mAdapter = SimpleListAdapter()

    override fun setupViewModel() {
        binding.viewmodel = viewModel
    }

    override fun setupView() {
        mToolbarTitle.text = "这是列表页面"
        recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@SimpleListActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        viewModel.dataList.observe(this) {
            mAdapter.submitList(it)
            if(it.size >8){
            recyclerView.smoothScrollToPosition(it.size - 1)
            }
        }
       mToolbarRightTitle.text = "添加数据"
    }

    override fun setupListener() {
        mToolbarRightTitle.throttleFirstClick{
            viewModel.createData()
        }
    }

    override fun getLayoutId() = R.layout.simple_activity_list
}