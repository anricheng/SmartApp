package com.capgemini.module_sample.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LOGIN
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityLoginBinding
import com.capgemini.module_sample.viewmodel.SimpleLoginViewModel

@Route(path = SAMPLE_LOGIN)
class SimpleLoginActivity : BaseDataBindingActivity<SimpleActivityLoginBinding>() {

    private val mViewModel by viewModels<SimpleLoginViewModel>()


    override fun setupViewModel() {
        binding.viewmodel = mViewModel
    }


    override fun setupView() {
        mToolbarTitle.text = "Login"
    }

    override fun getLayoutId(): Int = R.layout.simple_activity_login
}