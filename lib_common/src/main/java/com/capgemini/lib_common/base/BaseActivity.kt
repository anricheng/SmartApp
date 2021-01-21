package com.capgemini.lib_common.base

import android.app.Activity
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.*
import com.zackratos.ultimatebarx.library.addStatusBarTopPadding
import kotlinx.android.synthetic.main.include_toolbar.*

inline val Activity.mToolbar get() = toolbar
inline val Activity.mToolbarTitle get() = toolbarTitle
inline val Activity.mToolbarRightTitle get() = toolbarSubtitle

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
        toolbar.notNull { toolbar ->
            toolbar.addStatusBarTopPadding()
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


