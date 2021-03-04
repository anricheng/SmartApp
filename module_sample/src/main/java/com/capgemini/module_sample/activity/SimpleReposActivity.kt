package com.capgemini.module_sample.activity

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_common.databinding.onClick
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleReposAdapter
import com.capgemini.module_sample.adapter.setOnClickListener
import com.capgemini.module_sample.databinding.SimpleActivityReposBinding
import com.capgemini.module_sample.viewmodel.SimpleReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS)
class SimpleReposActivity : BaseDataBindingActivity<SimpleActivityReposBinding>() {
    private val viewModel by viewModels<SimpleReposViewModel>()
    private val mAdapter = SimpleReposAdapter()

    override fun setupViewModel() {
        binding.viewmodel = viewModel
        mAdapter.setItemClickListener(object : setOnClickListener {
            override fun Onclick(s: String?) {
                var name = s
                NavigationHelper.navigation(SampleModuleARouterPath.SAMPLE_REPOS_DETAILS)
            }
        })
    }

    override fun setupView() {
        mToolbarTitle.text = "仓库"
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SimpleReposActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        viewModel.dataList.observe(this) {
            mAdapter.submitList(it)
        }

        viewModel.getData()

    }

    override fun getLayoutId() = R.layout.simple_activity_repos
}