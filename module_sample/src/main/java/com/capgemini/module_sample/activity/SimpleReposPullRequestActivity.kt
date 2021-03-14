package com.capgemini.module_sample.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.entity.ReposPullRequestItem
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_Pull_Request
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleReposPullRequestAdapter
import com.capgemini.module_sample.databinding.SimpleActivityPullRequestBinding
import com.capgemini.module_sample.viewmodel.SimpleReposPullRequestModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS_Pull_Request)
class SimpleReposPullRequestActivity : BaseDataBindingActivity<SimpleActivityPullRequestBinding>() {
    private val viewModel by viewModels<SimpleReposPullRequestModel>()
    private val mAdapter = SimpleReposPullRequestAdapter()
    val username: String = "octocat"
    val reponame: String = "hello-world"
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
            binding.item = it
        }
        viewModel.dataList.observe(this) {
            mAdapter.submitList(it)
        }
        viewModel.getpullRequestData(username,reponame)
    }

    override fun getLayoutId() = R.layout.simple_activity_pull_request
}