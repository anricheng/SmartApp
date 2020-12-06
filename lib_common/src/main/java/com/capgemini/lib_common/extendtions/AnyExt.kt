package com.capgemini.lib_common.extendtions

import android.widget.Toast
import androidx.annotation.StringRes
import com.blankj.utilcode.util.PermissionUtils
import com.capgemini.lib_common.base.GlobalApplicationContext
import loading.Loading

sealed class NullableAnyExt<out R>

object NullAny : NullableAnyExt<Nothing>()
class NotNullAny<R>(val data: R) : NullableAnyExt<R>()

inline fun <T, R> T.notNull(block: (T) -> R) =
    this?.let {
        NotNullAny(block(this))
    } ?: let {
        NullAny
    }

inline fun <T, R> T.isNull(block: () -> R) =
    this?.let {
        NotNullAny(block())
    } ?: let {
        NullAny
    }

inline fun <R> NullableAnyExt<R>.otherwise(block: () -> R): R = when (this) {
    is NullAny -> block()
    is NotNullAny -> this.data
}


inline fun requestPermission( vararg permissions: String, crossinline block: (Boolean) -> Unit) {
    PermissionUtils.permission(*permissions).callback(object : PermissionUtils.SimpleCallback {
        override fun onDenied() {
            block(false)
        }

        override fun onGranted() {
            block(true)
        }
    }).request()
}



 fun toastSt(text: String) = Toast.makeText(GlobalApplicationContext, text, Toast.LENGTH_SHORT).show()

 fun toastLg(text: String) = Toast.makeText(GlobalApplicationContext, text, Toast.LENGTH_LONG).show()

fun showLoading(@StringRes resId: Int = com.capgemini.lib_widget.R.string.loadingContent)=Loading.show(content = resId)
fun hideLoading(@StringRes resId: Int = com.capgemini.lib_widget.R.string.loadingContent)=Loading.dismiss()