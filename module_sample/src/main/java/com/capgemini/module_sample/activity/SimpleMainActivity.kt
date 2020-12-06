package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.capgemini.lib_common.extendtions.throttleFirstClick
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_MAIN
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityMainBinding
import com.capgemini.module_sample.viewmodel.SimpleMainActivityViewModel
import com.gyf.immersionbar.ktx.hideStatusBar
import com.gyf.immersionbar.ktx.showStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_main.*

@AndroidEntryPoint
@Route(path = SAMPLE_MAIN)
class SimpleMainActivity : BaseDataBindingActivity<SimpleActivityMainBinding>() {
    private val viewModel by viewModels<SimpleMainActivityViewModel>()
    private var isStatusBar = false

    override fun setupViewModel() {
        binding.viewModel = viewModel
        setupToolbarTitle("样例主页")
    }



    override fun setupListener() {
        toggleStatusBar.throttleFirstClick {
            isStatusBar = isStatusBar.isTrue {
                hideStatusBar()
                false
            }.otherwise {
                showStatusBar()
                true
            }
        }
    }
    override fun getLayoutId()=R.layout.simple_activity_main
}