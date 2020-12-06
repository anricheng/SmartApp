package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
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
        setupToolbarTitle("这是列表页面")
        recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@SimpleListActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        viewModel.dataList.observe(this) {
            mAdapter.submitList(it)
            recyclerView.smoothScrollToPosition(it.size - 1)
        }

        setupToolbarRightSubtitle("添加数据") {
            viewModel.createData()
        }
    }

    override fun getLayoutId() = R.layout.simple_activity_list
}