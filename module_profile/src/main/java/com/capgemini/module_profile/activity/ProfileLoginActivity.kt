package com.capgemini.module_profile.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_base.arouter.ProfileModuleARouterPath.Companion.PROFILE_LOGIN
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.capgemini.lib_common.extendtions.throttleFirstClick
import com.capgemini.module_profile.R
import com.capgemini.module_profile.databinding.ProfileActivityLoginBinding
import com.capgemini.module_profile.viewmodel.LoginViewModel
import com.gyf.immersionbar.ktx.hideStatusBar
import com.gyf.immersionbar.ktx.showStatusBar
import kotlinx.android.synthetic.main.profile_activity_login.*

@Route(path = PROFILE_LOGIN)
class ProfileLoginActivity : BaseDataBindingActivity<ProfileActivityLoginBinding>() {
    private val mViewModel by viewModels<LoginViewModel>()
    private var isStatusBar = false

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

    override fun getLayoutId(): Int = R.layout.profile_activity_login
}