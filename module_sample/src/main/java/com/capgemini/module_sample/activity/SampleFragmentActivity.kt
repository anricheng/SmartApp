package com.capgemini.module_sample.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseActivity
import com.capgemini.lib_common.extendtions.replaceFragment
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_FRAGMENT
import com.capgemini.module_sample.R
import com.capgemini.module_sample.fragment.SimpleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = SAMPLE_FRAGMENT)
class SampleFragmentActivity : BaseActivity() {
    
    override fun getLayoutId(): Int = R.layout.simple_fragment

    override fun setupView() {
        replaceFragment(R.id.fragment_container, SimpleFragment.newInstance("zhou1"))
    }

    override fun isMultiFragmentActivity()= true
}