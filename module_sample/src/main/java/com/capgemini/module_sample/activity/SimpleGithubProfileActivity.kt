package com.capgemini.module_sample.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_GITHUB_PROFILE
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleRecentEventAdapter
import com.capgemini.module_sample.databinding.SimpleActivityGithubProfileBinding
import com.capgemini.module_sample.viewmodel.SimpleRecentEventViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_github_profile.*
import retrofit2.http.Path

@AndroidEntryPoint
@Route(path = SAMPLE_GITHUB_PROFILE)
class SimpleGithubProfileActivity : BaseDataBindingActivity<SimpleActivityGithubProfileBinding>() {

    private val viewModel by viewModels<SimpleRecentEventViewModel>()

    private val mAdapter = SimpleRecentEventAdapter()

    override fun setupViewModel() {
        binding.viewModel = viewModel
    }

    override fun setupView() {
        //viewModel.visibility.value = false
        mToolbarTitle.text = "个人中心"
        profileRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SimpleGithubProfileActivity, RecyclerView.HORIZONTAL, false)
            adapter = mAdapter
        }

        viewModel.dataList.observe(this){
            mAdapter.submitList(it)
        }

        viewModel.getUser()
        viewModel.getRecentEvent()

    }


    override fun getLayoutId() = R.layout.simple_activity_github_profile
}