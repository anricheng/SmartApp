package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_COMMUNITY
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityCommunityHomeBinding
import com.capgemini.module_sample.viewmodel.SimpleCommunityHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = SAMPLE_COMMUNITY)
class SimpleCommunityHomeActivity : BaseDataBindingActivity<SimpleActivityCommunityHomeBinding>() {
    private val viewModel by viewModels<SimpleCommunityHomeViewModel>()

    override fun setupViewModel() {
        binding.viewmodel = viewModel
        mToolbarTitle.text = ""
    }

    override fun getLayoutId() = R.layout.simple_activity_community_home
}