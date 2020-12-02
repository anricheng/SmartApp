package com.capgemini.module_profile.fragment

import android.os.Bundle
import com.capgemini.lib_common.base.BaseFragment
import com.capgemini.module_profile.R


class ProfileSampleFragment : BaseFragment() {

    companion object {
        fun newInstance(text: String): BaseFragment {
            return ProfileSampleFragment().apply {
                arguments = Bundle().apply {
                    putString("param", text)
                }
            }
        }
    }

    override fun setupToolBarBackIcon(resId: Int) = null

    override fun getLayoutId() = R.layout.profile_fragment_sample

    override fun setupView() {
        super.setupView()
        setupToolbarTitle("fragment${arguments?.get("param")}")
    }
}