package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.capgemini.lib_common.extendtions.throttleFirstClick
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LOGIN
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityLoginBinding
import com.capgemini.module_sample.viewmodel.SimpleLoginViewModel
import com.gyf.immersionbar.ktx.hideStatusBar
import com.gyf.immersionbar.ktx.showStatusBar
import kotlinx.android.synthetic.main.simple_activity_login.*

@Route(path = SAMPLE_LOGIN)
class SimpleLoginActivity : BaseDataBindingActivity<SimpleActivityLoginBinding>() {

    private var isStatusBar = false
    private val mViewModel by viewModels<SimpleLoginViewModel>()


    override fun setupViewModel() {
        binding.viewmodel = mViewModel
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

    override fun setupView() {
        setupToolbarTitle("Login")
        setupToolbarBackground(com.capgemini.lib_common.R.color.colorPrimary)
    }

    override fun getLayoutId(): Int = R.layout.simple_activity_login
}