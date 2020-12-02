package com.capgemini.lib_common.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar

interface IBase {

    val immersionBarContext: Any

    /**
     * if setImmersionBarConfig() doesn't meet requirement then override this method
     */
    fun setupImmersionBar() {
        val block: ImmersionBar.() -> Unit = {
            setupImmersionBarConfig().apply {
                isImmersionBarEnabled.isTrue {
                    isTransparentBar.isTrue {
                        //状态栏覆盖在布局之上，重叠，透明状态栏
                        transparentBar()
                    }.otherwise {
                        //状态栏在布局之上，不重叠，状态栏可设置颜色
                        fitsSystemWindows(true)
                        statusBarColor(statusBarColor)
                    }
                    statusBarDarkFont(isStatusBarDarkFont)
                    keyboardEnable(isKeyboardEnabled)
                }
            }
        }
        when (immersionBarContext) {
            is AppCompatActivity -> (immersionBarContext as AppCompatActivity).immersionBar(block)
            is Fragment -> (immersionBarContext as Fragment).immersionBar(block)
            else -> throw IllegalArgumentException("the context should be Fragment or Activity and not null")
        }
    }

    /**
     * set up configuration for ImmersionBar
     */
    fun setupImmersionBarConfig(): ImmersionBarConfig = ImmersionBarConfig()

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

    fun setUpToolbar(){}
}

/**
 * @param statusBarColor 状态栏的背景色，默认为白色
 * @param isKeyboardEnabled 是否有键盘弹出，默认无
 * @param isTransparentBar 是否是透明状态栏，默认否
 * @param isImmersionBarEnabled 是否使用ImmersionBar，默认使用
 */
data class ImmersionBarConfig(
    val statusBarColor: Int = R.color.colorPrimary,
    val isStatusBarDarkFont: Boolean = false,
    val isKeyboardEnabled: Boolean = false,
    val isTransparentBar: Boolean = false,
    val isImmersionBarEnabled: Boolean = true
)