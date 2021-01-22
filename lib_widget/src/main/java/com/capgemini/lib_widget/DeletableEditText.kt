package com.capgemini.lib_widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText

class DeletableEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyle) {
    lateinit var mDeleteDrawable: Drawable

    init {
        val typeArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.DeletableEditText)
        mDeleteDrawable = resources.getDrawable(
            typeArray.getResourceId(
                R.styleable.DeletableEditText_delete_icon,
                R.drawable.delete
            )
        )
        mDeleteDrawable.setBounds(0, 0, 50, 50)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        val isTextLengthGreatThanZero = text?.isNotEmpty() ?: { false }
        setDeleteIconVisible(hasFocus(), isTextLengthGreatThanZero as Boolean)
    }

    private fun setDeleteIconVisible(hasFocus: Boolean, isDeleteIconVisible: Boolean) {
        setCompoundDrawables(
            null, null, if (isDeleteIconVisible) {
                mDeleteDrawable
            } else {
                null
            }, null
        )
        invalidate()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        val isTextLengthGreatThanZero = text?.isNotEmpty() ?: { false }
        setDeleteIconVisible(hasFocus(), isTextLengthGreatThanZero as Boolean)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            if ((event.x.toInt() in (width - paddingRight - mDeleteDrawable.bounds.width())..width - paddingRight)) {
                setText("")
            }
        }
        return super.onTouchEvent(event)
    }
}