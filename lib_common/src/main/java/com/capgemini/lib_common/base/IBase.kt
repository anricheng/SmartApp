package com.capgemini.lib_common.base

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capgemini.lib_common.R
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.library.bean.BarConfig

interface IBase {

    val immersionBarContext: Any
    /**
     * if setImmersionBarConfig() doesn't meet requirement then override this method
     */
    fun setupImmersionBar() {
        val config = BarConfig.newInstance()
            .fitWindow(true)
            .transparent()
            .light(false)
        when (immersionBarContext) {
            is AppCompatActivity -> UltimateBarX.with(immersionBarContext as AppCompatActivity)
                .config(config)
                .applyStatusBar()
            is Fragment -> UltimateBarX.with(immersionBarContext as Fragment)
                .config(config)
                .applyStatusBar()
            else -> throw IllegalArgumentException("the context should be Fragment or Activity and not null")
        }
    }

    /**
     * The fun should be override only when Fragment/Activity context scope is a must
     */
    fun setupListener() {}

    /**
     * set up view
     */
    fun setupView() {}

    /**
     * set up layout resource id
     */
    fun getLayoutId(): Int

    fun setupToolBarBackIcon(@DrawableRes resId: Int = R.drawable.ic_back): Int? = resId
}