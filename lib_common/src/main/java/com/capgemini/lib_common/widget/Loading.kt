package com.capgemini.lib_common.widget

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Looper
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ConvertUtils
import com.capgemini.lib_common.R
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

object Loading {
    /**
     * @param activity   当前展示loading的activity
     * @param message    loading文本
     * @param cancelable loading是否可以返回键 或点击空白区域(仅holdable为true生效)退出
     * @param holdable   loading是否拦截事件
     */
    fun show(activity: Activity, message: String?, cancelable: Boolean, holdable: Boolean) {
        show(activity.findViewById<View>(android.R.id.content), message, cancelable, holdable)
    }

    /**
     * @param activity 当前展示loading的activity
     * @param message  loading文本
     */
    fun show(activity: Activity, message: String?) {
        show(activity.findViewById<View>(android.R.id.content), message, false, true)
    }

    /**
     * @param activity 当前展示loading的activity
     */
    fun show(activity: Activity) {
        show(
            activity.findViewById<View>(android.R.id.content),
            activity.getString(R.string.common_loading_content),
            false,
            true
        )
    }

    /**
     * @param activity 当前展示loading的activity
     * @param justIcon 仅展示icon 没有文字
     */
    fun show(activity: Activity=ActivityUtils.getTopActivity(), justIcon: Boolean) {
        show(
            activity.findViewById<View>(android.R.id.content),
            if (justIcon) null else activity.getString(R.string.common_loading_content),
            false,
            true
        )
    }

    /**
     * @param root     当前展示loading的view
     * @param justIcon 仅展示icon 没有文字
     */
    fun show(root: View, justIcon: Boolean) {
        show(
            root,
            if (justIcon) null else root.resources.getString(R.string.common_loading_content),
            false,
            true
        )
    }

    /**
     * @param root       当前展示loading的view
     * @param justIcon   仅展示icon 没有文字
     * @param cancelable loading是否可以返回键 或点击空白区域(仅holdable为true生效)退出
     * @param holdable   loading是否拦截事件
     */
    fun show(root: View, justIcon: Boolean, cancelable: Boolean, holdable: Boolean) {
        show(
            root,
            if (justIcon) null else root.resources.getString(R.string.common_loading_content),
            cancelable,
            holdable
        )
    }

    /**
     * @param root       当前展示loading的view
     * @param cancelable loading是否可以返回键 或点击空白区域(仅holdable为true生效)退出
     * @param holdable   loading是否拦截事件
     */
    fun show(root: View, cancelable: Boolean, holdable: Boolean) {
        show(root, root.resources.getString(R.string.common_loading_content), cancelable, holdable)
    }
    /**
     * @param root       当前展示loading的view
     * @param message    loading文本
     * @param cancelable loading是否可以返回键 或点击空白区域(仅holdable为true生效)退出
     * @param holdable   loading是否拦截事件
     */
    /**
     * @param root 当前展示loading的view
     */
    @JvmOverloads
    fun show(
        root: View,
        message: String? = root.resources.getString(R.string.common_loading_content),
        cancelable: Boolean = false,
        holdable: Boolean = true
    ) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showImpl(root, message, cancelable, holdable)
        } else {
            root.post { showImpl(root, message, cancelable, holdable) }
        }
    }

    private fun showImpl(root: View, message: String?, cancelable: Boolean, holdable: Boolean) {
        Objects.requireNonNull(root)
        if (root is LoadingFrameLayout) {
            root.showLoading(message, cancelable, holdable)
            return
        }
        val parent = root.parent ?: throw RuntimeException("root view must have a parent")
        if (parent is LoadingFrameLayout) {
            parent.showLoading(message, cancelable, holdable)
        } else if (parent is ViewGroup) {
            val rootLp = root.layoutParams
            val index = parent.indexOfChild(root)
            parent.removeView(root)
            val frameLayout = LoadingFrameLayout(root.context)
            frameLayout.addView(
                root,
                FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            parent.addView(frameLayout, index, rootLp)
            frameLayout.showLoading(message, cancelable, holdable)
            if (parent is RelativeLayout) {
                frameLayout.id = root.id
            }
        }
    }

    fun dismiss(root: View) {
        Objects.requireNonNull(root)
        if (root is LoadingFrameLayout) {
            root.dismissLoading()
            return
        }
        val parent = root.parent
        if (parent is LoadingFrameLayout) {
            parent.dismissLoading()
        }
    }

    fun dismiss(root: Activity) {
        dismiss(root.findViewById<View>(android.R.id.content))
    }

    private class LoadingFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : FrameLayout(context, attrs, defStyleAttr) {
        private var mMessage: TextView? = null
        private var outside: FrameLayout? = null
        private var content: View? = null
        private var count: AtomicInteger? = null
        private var isShowing: AtomicBoolean? = null
        private fun findContent() {
            if (content == null) for (i in 0 until childCount) {
                val child = getChildAt(i)
                if (child === outside) continue
                content = child
                break
            }
        }

        private val mListener = OnClickListener { v ->
            if (v.getTag(CANCELABLE) as Boolean) {
                outside!!.visibility = GONE
            }
        }

        override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_BACK && outside!!.getTag(CANCELABLE) as Boolean) {
                outside!!.visibility = GONE
            }
            return super.onKeyDown(keyCode, event)
        }

        private fun initLoading() {
            count = AtomicInteger(0)
            isShowing = AtomicBoolean(false)
            setBackgroundColor(Color.TRANSPARENT)
            LayoutInflater.from(context).inflate(R.layout.common_widget_loading, this)
            mMessage = findViewById(R.id.tv_message)
            outside = findViewById(R.id.touch_outside)
            //加上一个margin toolbar + status bar 的高度，避免toolbar 返回键不起作用，其中 toolbar 高度是50dp,设置一个60作为buffer
            val layoutParams = outside?.layoutParams
            var marginLayoutParams: MarginLayoutParams? = null
            marginLayoutParams = if (layoutParams is MarginLayoutParams) {
                layoutParams
            } else {
                MarginLayoutParams(layoutParams)
            }
            marginLayoutParams!!.setMargins(
                0,
                BarUtils.getStatusBarHeight() + ConvertUtils.dp2px(60f),
                0,
                0
            )
            outside?.setOnClickListener(mListener)
        }

        private fun show(message: String?, cancelable: Boolean, holdable: Boolean) {
            count!!.set(count!!.get() + 1)
            if (isShowing!!.get()) {
                return
            }
            outside!!.visibility = VISIBLE
            outside!!.bringToFront()
            if (message == null || message.isEmpty()) mMessage!!.visibility = GONE
            mMessage!!.text = message
            outside!!.isClickable = holdable
            outside!!.setTag(CANCELABLE, cancelable)
            outside!!.setTag(HOLDABLE, holdable)
            isShowing!!.set(true)
        }

        fun showLoading(message: String?, cancelable: Boolean, holdable: Boolean) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                show(message, cancelable, holdable)
            } else {
                post { show(message, cancelable, holdable) }
            }
        }

        private fun dismiss() {
            count!!.set(count!!.get() - 1)
            if (count!!.get() != 0) return
            findContent()
            content!!.bringToFront()
            content!!.visibility = VISIBLE
            outside!!.visibility = INVISIBLE
            isShowing!!.set(false)
        }

        fun dismissLoading() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                dismiss()
            } else {
                post { dismiss() }
            }
        }

        companion object {
            private val CANCELABLE = R.id.common_loadingCancelable
            private val HOLDABLE = R.id.common_loadingHoldable
        }

        init {
            initLoading()
        }
    }
}