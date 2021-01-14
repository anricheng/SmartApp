package com.capgemini.module_sample.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.capgemini.lib_common.base.BaseDataBindingFragment
import com.capgemini.lib_common.base.BaseFragment
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleFragmentSampleBinding
import com.capgemini.module_sample.viewmodel.SimpleCommunityHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimpleFragment : BaseDataBindingFragment<SimpleFragmentSampleBinding>() {
    private val viewModel by viewModels<SimpleCommunityHomeViewModel>()

    companion object {
        fun newInstance(text: String): BaseFragment {
            return SimpleFragment().apply {
                arguments = Bundle().apply {
                    putString("param", text)
                }
            }
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
    }

    override fun getLayoutId() = R.layout.simple_fragment_sample

    override fun setupView() {
        super.setupView()
        setupToolbarTitle("fragment${arguments?.get("param")}")
    }
}