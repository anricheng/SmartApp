package com.capgemini.module_sample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.ReposDetails
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.BooleanExt
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.capgemini.lib_communicate.arouter.RouterExtra
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_DETAILS
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityReposDetailsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_repos_details.*

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS_DETAILS)
class SimpleReposDetailsActivity : BaseDataBindingActivity<SimpleActivityReposDetailsBinding>() {

    override fun getLayoutId(): Int = R.layout.simple_activity_repos_details

    @JvmField
    @Autowired
    var reposDetails: ReposDetails? = null
    var bundle:Bundle? = null
    var gson: Gson? = Gson()
    var item:RepositoriesItem? = null
    var itemToString:String? = null
    override fun setupViewModel() {
        bundle = intent.getBundleExtra("bundle")
        itemToString = bundle?.getString("abc")
        item = gson?.fromJson(itemToString,RepositoriesItem::class.java)
        Log.v("hello",item.toString())
        reposDetails?.name == item?.name
        reposDetails?.archiveUrl == item?.archive_url
        reposDetails?.isPrivate == item?.isPrivate
        reposDetails?.stargazersCount == item?.stargazers_count
        reposDetails?.owner?.login == item?.owner?.login
        reposDetails?.forksCount == item?.forks_count
        reposDetails?.openIssuesCount == item?.open_issues_count
        reposDetails?.watchersCount == item?.watchers_count
        reposDetails?.description == item?.description
        binding.reposDetails = reposDetails
        textViewUser.setText(item?.owner?.login)
        reposDetails?.isPrivate?.isTrue{
            textViewPrivate.setText("私人")
        }?.otherwise {
            textViewPrivate.setText("公共")
        }

    }
}