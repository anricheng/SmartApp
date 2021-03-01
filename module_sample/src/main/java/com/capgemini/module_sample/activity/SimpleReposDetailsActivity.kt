package com.capgemini.module_sample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.api.ReposDetailsApi
import com.capgemini.entity.ReposDetails
import com.capgemini.http.RetrofitManager
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_DETAILS
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityReposDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_repos_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS_DETAILS)
class SimpleReposDetailsActivity : AppCompatActivity() {

    @JvmField
    @Autowired(name = "reposDetails")
    var reposDetails: ReposDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding =DataBindingUtil.setContentView<SimpleActivityReposDetailsBinding>(this,R.layout.simple_activity_repos_details)

        ARouter.getInstance().inject(this)
        dataBinding.reposDetails = reposDetails
        when{
            reposDetails?.private == true -> textViewPrivate.setText("私人")
            reposDetails?.private == false -> textViewPrivate.setText("公共")
        }

        //Log.d("拿到数据2",intent.extras?.getParcelable<ReposDetails>("reposDetails").toString())

    }
}