package com.capgemini.com.activity

import androidx.activity.viewModels
import com.capgemini.com.R
import com.capgemini.com.databinding.MainActivityModuleEntranceBinding
import com.capgemini.com.viewmodel.ModuleEntranceViewModel
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.toastSt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModuleEntranceActivity : BaseDataBindingActivity<MainActivityModuleEntranceBinding>() {

    private val mViewModel by viewModels<ModuleEntranceViewModel>()

    override fun setupViewModel() {
        binding.viewModel = mViewModel
    }

    override fun setupToolBarBackIcon(resId: Int) = null

    override fun setupView() {
        setupToolbarTitle("测试主页")
        setupToolbarRightSubtitle("QA_VERSION") {
            toastSt("仅供测试 请勿用于非法 ")
        }
        setupToolbarBackground(com.capgemini.lib_common.R.color.colorPrimary)
    }

    override fun getLayoutId(): Int = R.layout.main_activity_module_entrance
}