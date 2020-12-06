package com.capgemini.lib_common.databinding

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.notNull
import com.capgemini.lib_common.extendtions.otherwise
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit


const val THROTTLE_FIRST_CLICK_TIME_SECONDS = 500L


@BindingAdapter("milliSeconds", "throttleFirstClick", requireAll = false)
fun onClick(view: View, milliSeconds: Long, listener: View.OnClickListener) {

    val realMilliSeconds = if (milliSeconds <= 0) {
        THROTTLE_FIRST_CLICK_TIME_SECONDS
    } else {
        milliSeconds
    }
    view.clicks().throttleFirst(realMilliSeconds, TimeUnit.MILLISECONDS).subscribe {
        listener.onClick(view)
    }
}

@BindingConversion
fun convertColorToDrawable(color: Int): ColorDrawable {
    return ColorDrawable(color)
}

@BindingAdapter("app:error")
fun error(view: TextInputLayout, errorMessage: String?) {
    errorMessage.notNull {
        view.isErrorEnabled = true
        view.error = errorMessage
    }.otherwise {
        view.isErrorEnabled = false

    }
}


@BindingAdapter("clearOnFocusAndDispatch")
fun clearOnFocusAndDispatch(view: EditText, listener: View.OnFocusChangeListener?) {
    view.onFocusChangeListener = View.OnFocusChangeListener { focusedView, hasFocus ->
        val textView = focusedView as TextView
        if (hasFocus) {
            // Delete contents of the EditText if the focus entered.
            view.setTag(R.id.previousValue, textView.text)
            textView.text = ""
        } else {
            if (textView.text.isEmpty()) {
                val tag: CharSequence? = textView.getTag(R.id.previousValue) as CharSequence
                textView.text = tag ?: ""
            }
            // If the focus left, update the listener
            listener?.onFocusChange(focusedView, hasFocus)
        }
    }
}


@BindingAdapter("clearTextOnFocus")
fun EditText.clearTextOnFocus(enabled: Boolean) {
    if (enabled) {
        clearOnFocusAndDispatch(this, null)
    } else {
        this.onFocusChangeListener = null
    }
}


@BindingAdapter("hideKeyboardOnInputDone")
fun hideKeyboardOnInputDone(view: EditText, enabled: Boolean) {
    if (!enabled) return
    val listener = TextView.OnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            view.clearFocus()
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        false
    }
    view.setOnEditorActionListener(listener)
}


@BindingAdapter("invisibleUnless")
fun invisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}


@BindingAdapter(value = ["android:max", "android:progress"], requireAll = true)
fun updateProgress(progressBar: ProgressBar, max: Int, progress: Int) {
    progressBar.max = max
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        progressBar.setProgress(progress, false)
    } else {
        progressBar.progress = progress
    }
}

@BindingAdapter("loseFocusWhen")
fun loseFocusWhen(view: EditText, condition: Boolean) {
    if (condition) view.clearFocus()
}