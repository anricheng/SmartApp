package com.capgemini.module_sample.activity

import android.view.View
import androidx.core.widget.NestedScrollView
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.lib_common.base.BaseActivity
import com.capgemini.lib_common.base.mToolbar
import com.capgemini.lib_common.base.mToolbarTitle
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_SCROLL
import com.capgemini.module_sample.R
import com.zackratos.ultimatebarx.library.UltimateBarX
import kotlinx.android.synthetic.main.simple_activity_scroll.*

@Route(path = SAMPLE_SCROLL)
class SimpleScrollActivity : BaseActivity() {

    override fun setupView() {
        mToolbarTitle.text = "滑动主页"
        mToolbar.visibility = View.INVISIBLE
    }

    override fun setupListener() {
        scrollView.setOnScrollChangeListener { _: NestedScrollView?, _, scrollY: Int, _, oldScrollY: Int ->
            val height = imageView.height - mToolbar.height
            if (height in (oldScrollY + 1)..scrollY) {
                UltimateBarX.get(this)
                    .light(true)
                    .applyStatusBar()
                mToolbar.visibility = View.VISIBLE
            } else if (height in (scrollY + 1)..oldScrollY) {
                UltimateBarX.get(this)
                    .light(false)
                    .applyStatusBar()
                mToolbar.visibility = View.INVISIBLE
            }
        }
    }

    override fun getLayoutId() = R.layout.simple_activity_scroll
}