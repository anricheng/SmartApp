package com.capgemini.lib_common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseDataBindingFragment<T : ViewDataBinding> : BaseFragment() {
    protected lateinit var binding: T

    final override fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), getLayoutId(), container, false)
        binding.lifecycleOwner = this
        setupViewModel()
        return binding.root
    }

    protected open fun setupViewModel() {}

}