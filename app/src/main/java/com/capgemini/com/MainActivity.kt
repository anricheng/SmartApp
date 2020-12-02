package com.capgemini.com

import androidx.activity.viewModels
import com.capgemini.com.databinding.ActivityMainBinding
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.toastSt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainActivityViewModel>()

    override fun setupViewModel() {
        super.setupViewModel()
        binding.viewModel = mViewModel
    }

    override fun setupToolBarBackIcon(resId: Int)=null

    override fun setupView() {
        setupToolbarTitle("Home Page")
        setupToolbarRightSubtitle("subtitle") {
            toastSt("THis is just a sample test")
        }
        setupToolbarBackground(com.capgemini.lib_common.R.color.colorPrimary)
    }

    override fun getLayoutId(): Int = R.layout.activity_main
}