package com.capgemini.lib_common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.*
import kotlinx.android.synthetic.main.common_include_toolbar.*


abstract class BaseActivity : AppCompatActivity(), IBase {
    override val immersionBarContext: Any
        get() = this

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this is BaseDataBindingActivity<*>).isTrue {
            setupDataBinding()
        }.otherwise {
            setContentView(getLayoutId())
        }
        commonToolbar.notNull { toolbar ->
            setupToolBarBackIcon().notNull {
                toolbar.setNavigationIcon(it!!)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }.otherwise {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
            supportActionBar?.setDisplayShowTitleEnabled(false)

        }
        setupImmersionBar()
        setupListener()
        setupView()
    }

    /************toolbar start ***********/

    protected open fun setupToolBarBackIcon(@DrawableRes resId: Int = R.drawable.ic_back): Int? = resId

    protected open fun setupToolbarRightSubtitle(text: String, listener: View.OnClickListener) {
        commonToolbarRightSubtitle.apply {
            this.text = text
            this.throttleFirstClick(listener =listener)
        }
    }

    protected open fun setupToolbarTitle(text: String) {
        commonToolbarTitle.text = text
    }

    protected open fun setupToolbarBackground(
        @ColorRes colorId: Int,
        @DrawableRes drawableId: Int = -1
    ) {
        (drawableId != -1).isTrue {
            commonToolbar.setBackgroundE(drawableId)
        }.otherwise {
            commonToolbar.setBackgroundColorE(colorId)
        }
    }

    /************toolbar end ***********/

    open fun isMultiFragmentActivity() = false

    protected open fun setupDataBinding() {}

    override fun onBackPressed() {
        //single activity multiple fragment can pop back correctly
        if (isMultiFragmentActivity()) {
            supportFragmentManager.apply {
                (backStackEntryCount > 1).isTrue {
                    popBackStack()
                }.otherwise {
                    finish()
                }
            }
        } else {
            super.onBackPressed()
        }
    }

}


