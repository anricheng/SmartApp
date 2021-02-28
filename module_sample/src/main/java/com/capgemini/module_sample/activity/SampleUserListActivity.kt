package com.capgemini.module_sample.activity

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.UserInformation
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LIST
import com.capgemini.module_sample.adapter.SimpleListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = SAMPLE_LIST)
class SampleUserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    fun getUserImformation(): Unit {
        ARouter.getInstance().inject(this);
        var bundle: Bundle? = intent.getBundleExtra("bundle")
        var list:List<UserInformation> = bundle?.get("abc") as List<UserInformation>
        var:listy SimpleListAdapter
    }
}