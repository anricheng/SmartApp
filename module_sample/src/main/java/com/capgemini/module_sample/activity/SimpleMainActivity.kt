package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_MAIN
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityMainBinding
import com.capgemini.module_sample.viewmodel.SimpleMainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = SAMPLE_MAIN)
class SimpleMainActivity : BaseDataBindingActivity<SimpleActivityMainBinding>() {
    private val viewModel by viewModels<SimpleMainActivityViewModel>()
    override fun setupViewModel() {
        binding.viewModel = viewModel
    }
    override fun getLayoutId()=R.layout.simple_activity_main
}