package com.capgemini.lib_common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.*
import kotlinx.android.synthetic.main.common_include_toolbar.*


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
        commonToolbar.notNull { toolbar ->
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

    /************toolbar start ***********/

    protected open fun setupToolBarBackIcon(@DrawableRes resId: Int = R.drawable.ic_back): Int? = resId

    protected open fun setupToolbarRightSubtitle(text: String, listener: View.OnClickListener) {
        commonToolbarRightSubtitle.apply {
            this.text = text
            this.throttleFirstClick(listener =listener)
        }
    }

    protected open fun setupToolbarTitle(text: String) {
        commonToolbarTitle.notNull { it.text = text }
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

    protected open fun <T : View> findViewById(@IdRes id: Int): T {
        return mRootView.findViewById(id)
    }

    protected open fun setupDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = null


    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        var animation: TranslateAnimation? = null
        if (transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            animation = if (enter) {
                TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                )
            } else {
                TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                )
            }
        } else if (FragmentTransaction.TRANSIT_FRAGMENT_CLOSE === transit) {
            animation = if (enter) {
                TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                )
            } else {
                TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                )
            }
        }
        if (animation == null) {
            animation = TranslateAnimation(0f, 0f, 0f, 0f)
        }
        animation.duration = 300
        return animation
    }
}