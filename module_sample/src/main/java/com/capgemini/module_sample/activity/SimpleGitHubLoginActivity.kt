package com.capgemini.module_sample.activity

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseActivity
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_GITHUB_LOGIN
import com.capgemini.module_sample.BuildConfig
import com.capgemini.module_sample.R
import kotlinx.android.synthetic.main.simple_activity_github_login.*
import java.util.*

@Route(path = SAMPLE_GITHUB_LOGIN)
class SimpleGitHubLoginActivity : BaseActivity() {

    val randomState = UUID.randomUUID().toString()
    val uri = Uri.parse(BuildConfig.GITHUB_LOGIN+randomState)

    override fun setupListener() {
        gitHubLoginButton.setOnClickListener {
            Log.d("===", "点击监听")
            intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun getLayoutId() = R.layout.simple_activity_github_login

}