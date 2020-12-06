package com.capgemini.module_sample.fragment

import android.os.Bundle
import com.capgemini.lib_common.base.BaseFragment
import com.capgemini.module_sample.R


class SimpleFragment : BaseFragment() {

    companion object {
        fun newInstance(text: String): BaseFragment {
            return SimpleFragment().apply {
                arguments = Bundle().apply {
                    putString("param", text)
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.simple_fragment_sample

    override fun setupView() {
        super.setupView()
        setupToolbarTitle("fragment${arguments?.get("param")}")
    }
}