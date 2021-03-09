package com.capgemini.module_sample.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.ReposPullRequestItem
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_CREATE_Pull_Request
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_Pull_Request
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleReposPullRequestAdapter
import com.capgemini.module_sample.adapter.setOnClickListener
import com.capgemini.module_sample.databinding.SimpleActivityPullRequestBinding
import com.capgemini.module_sample.databinding.SimpleActivityReposBinding
import com.capgemini.module_sample.viewmodel.SimpleReposPullRequestModel
import com.capgemini.module_sample.viewmodel.SimpleReposViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS_Pull_Request)
class SimpleReposPullRequestActivity : BaseDataBindingActivity<SimpleActivityPullRequestBinding>() {
    private val viewModel by viewModels<SimpleReposPullRequestModel>()
    private val mAdapter = SimpleReposPullRequestAdapter()
    private var bundle: Bundle? = Bundle()
    private var pullRequestItem:ReposPullRequestItem? = null
    var pullRequestData = MutableLiveData<ReposPullRequestItem>()
    var itemToString:String? = null
    var gson:Gson? = Gson()
    override fun setupViewModel() {
       binding.viewmodle = viewModel

    }
    override fun setupView() {
        mToolbarTitle.text = "pull request"
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SimpleReposPullRequestActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
        viewModel.pullRequestData.observe(this){
            pullRequestItem = it
            binding.item = pullRequestItem
        }
        viewModel.dataList2.observe(this) {
            mAdapter.submitList(it)
        }
        viewModel.getpullRequestData()
    }

    override fun getLayoutId() = R.layout.simple_activity_pull_request
}