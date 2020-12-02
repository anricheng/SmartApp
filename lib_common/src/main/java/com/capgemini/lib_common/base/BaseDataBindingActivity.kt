package com.capgemini.lib_common.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingActivity<T : ViewDataBinding> : BaseActivity() {
    protected lateinit var binding: T

    final override fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        setupViewModel()
    }

    protected open fun setupViewModel() {}
}


