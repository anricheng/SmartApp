package com.capgemini.module_sample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.ReposDetails
import com.capgemini.lib_common.base.BaseDataBindingActivity
import com.capgemini.lib_common.extendtions.BooleanExt
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_DETAILS
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityReposDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_repos_details.*

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS_DETAILS)
class SimpleReposDetailsActivity : BaseDataBindingActivity<SimpleActivityReposDetailsBinding>() {

    override fun getLayoutId(): Int = R.layout.simple_activity_repos_details

    @JvmField
    @Autowired
    var reposDetails: ReposDetails? = null

    override fun setupViewModel() {
        ARouter.getInstance().inject(this)
        binding.reposDetails = reposDetails

        reposDetails?.isPrivate?.isTrue{
            textViewPrivate.setText("私人")
        }?.otherwise {
            textViewPrivate.setText("公共")
        }

    }
}