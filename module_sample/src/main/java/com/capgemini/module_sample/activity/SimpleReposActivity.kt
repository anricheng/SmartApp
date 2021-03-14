package com.capgemini.module_sample.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.RouterExtra.Companion.ItEMBUNDLE
import com.capgemini.lib_communicate.arouter.RouterExtra.Companion.ItEMSTRING
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS
import com.capgemini.module_sample.R
import com.capgemini.module_sample.adapter.SimpleReposAdapter
import com.capgemini.module_sample.adapter.setOnClickListener
import com.capgemini.module_sample.databinding.SimpleActivityReposBinding
import com.capgemini.module_sample.viewmodel.SimpleReposViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_list.*

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS)
class SimpleReposActivity : BaseDataBindingActivity<SimpleActivityReposBinding>() {
    private val viewModel by viewModels<SimpleReposViewModel>()
    private val mAdapter = SimpleReposAdapter()
    private var bundle:Bundle? = Bundle()
    var itemToString = String()
    var gson:Gson? = Gson()
    override fun setupViewModel() {
        binding.viewmodel = viewModel
    }
    override fun setupListener() {
        mAdapter.setItemClickListener(object : setOnClickListener {
            override fun Onclick(item: RepositoriesItem) {
                    itemToString = gson?.toJson(item)!!
                    bundle?.putString(ItEMSTRING, itemToString)
                    ARouter.getInstance().build(SampleModuleARouterPath.SAMPLE_REPOS_DETAILS).withBundle(ItEMBUNDLE, bundle).navigation()
            }
        }
        )
    }

    override fun setupView() {
        mToolbarTitle.text = "仓库"
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SimpleReposActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        viewModel.dataList.observe(this) {
            mAdapter.submitList(it)
        }

        viewModel.getData()

    }

    override fun getLayoutId() = R.layout.simple_activity_repos
}