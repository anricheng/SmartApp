package com.capgemini.lib_common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.*
import com.zackratos.ultimatebarx.library.addStatusBarTopPadding
import kotlinx.android.synthetic.main.include_toolbar.*

inline val Fragment.mToolbar get() = toolbar
inline val Fragment.mToolbarTitle get() = toolbarTitle
inline val Fragment.mToolbarRightTitle get() = toolbarSubtitle


abstract class BaseFragment : Fragment(), IBase {
    protected lateinit var mContainerActivity: AppCompatActivity
    protected lateinit var mRootView: View
    override val immersionBarContext: Any
        get() = this

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContainerActivity = (activity as AppCompatActivity)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setupDataBinding(inflater, container, savedInstanceState).notNull { it }
            .otherwise { inflater.inflate(getLayoutId(), container, false) }
        mRootView = view!!
        return view
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupImmersionBar()
        setupListener()
        setupView()
        toolbar.notNull { toolbar ->
            toolbar.addStatusBarTopPadding()
            setupToolBarBackIcon().notNull {
                toolbar.setNavigationIcon(it!!)
                mContainerActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }.otherwise {
                mContainerActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            mContainerActivity.setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                //single activity multiple fragment can pop back correctly
                mContainerActivity.supportFragmentManager.apply {
                    (backStackEntryCount > 1).isTrue {
                        popBackStack()
                    }.otherwise {
                        mContainerActivity.finish()
                    }
                }
            }
            mContainerActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    protected open fun setupDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = null

}