package com.capgemini.module_sample.activity

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseActivity
import com.capgemini.lib_common.extendtions.showLoading
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_GITHUB_LOGIN
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_MAIN
import com.capgemini.lib_communicate.sp.ProfileInfo
import com.capgemini.module_sample.BuildConfig
import com.capgemini.module_sample.R
import com.capgemini.module_sample.viewmodel.SimpleGithubLoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_github_login.*
import loading.Loading
import java.util.*

@AndroidEntryPoint
@Route(path = SAMPLE_GITHUB_LOGIN)
class SimpleGitHubLoginActivity : BaseActivity() {

    private val viewModel by viewModels<SimpleGithubLoginViewModel>()

    val randomState = UUID.randomUUID().toString()
    val uri = Uri.parse(BuildConfig.GITHUB_LOGIN+randomState)

    override fun setupListener() {
        gitHubLoginButton.setOnClickListener {
            Log.d("===", "点击监听")
            intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun setupView() {
        val uri = intent.data
        if (uri != null) {
            val code: String? = uri.getQueryParameter("code")
            Log.d("---------code",code.toString())
            ProfileInfo.accessToken = code.toString()
            NavigationHelper.navigation(SAMPLE_MAIN)
            viewModel.getToken()
        }
    }


    override fun getLayoutId() = R.layout.simple_activity_github_login

}