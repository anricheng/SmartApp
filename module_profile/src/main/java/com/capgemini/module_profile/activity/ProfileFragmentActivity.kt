package com.capgemini.module_profile.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_base.arouter.ProfileModuleARouterPath.Companion.PROFILE_SAMPLE
import com.capgemini.lib_common.base.BaseActivity
import com.capgemini.lib_common.extendtions.replaceFragment
import com.capgemini.module_profile.R
import com.capgemini.module_profile.fragment.ProfileSampleFragment

@Route(path = PROFILE_SAMPLE)
class ProfileFragmentActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.profile_activity_fragment

    override fun setupView() {
        replaceFragment(R.id.fragment_container, ProfileSampleFragment.newInstance("zhou1"))
    }

    override fun isMultiFragmentActivity()= true
}