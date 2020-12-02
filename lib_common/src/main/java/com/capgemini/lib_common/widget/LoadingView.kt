package com.capgemini.lib_common.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.capgemini.lib_common.R

internal class LoadingView : AppCompatImageView {
    private var mRotateDegrees = 0f
    private var mFrameTime = 0
    private var mNeedToUpdateView = false
    private var mUpdateViewRunnable: Runnable? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    private fun init() {
        setImageResource(R.drawable.common_ic_loading)
        mFrameTime = 1500 / 360
        mUpdateViewRunnable = object : Runnable {
            override fun run() {
                mRotateDegrees = mRotateDegrees - 5
                mRotateDegrees = if (mRotateDegrees < -360) mRotateDegrees else mRotateDegrees + 360
                invalidate()
                if (mNeedToUpdateView) {
                    postDelayed(this, mFrameTime.toLong())
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.rotate(mRotateDegrees, width / 2.toFloat(), height / 2.toFloat())
        super.onDraw(canvas)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mNeedToUpdateView = true
        post(mUpdateViewRunnable)
    }

    override fun onDetachedFromWindow() {
        mNeedToUpdateView = false
        super.onDetachedFromWindow()
    }
}