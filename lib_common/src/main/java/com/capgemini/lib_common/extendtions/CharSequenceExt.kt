package com.capgemini.lib_common.extendtions

import android.widget.TextView
import java.util.regex.Pattern

const val REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$"
const val REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$"

sealed class CharSequenceExt<out T>

object NullCharSequence : CharSequenceExt<Nothing>()
class NotNullOrEmptyCharSequence<T>(val data: T) : CharSequenceExt<T>()

inline fun <T> CharSequence?.notNullOrEmpty(block: (CharSequence) -> T) =
    this.isNullOrEmpty().isTrue { NullCharSequence }
        .otherwise { NotNullOrEmptyCharSequence(block(this!!)) }

inline fun <T> CharSequence?.isNullOrEmpty(block: () -> T) =
    this.isNullOrEmpty().isTrue { NullCharSequence }
        .otherwise { NotNullOrEmptyCharSequence(block()) }

inline fun <T> CharSequenceExt<T>.otherwise(block: () -> T): T =
    when (this) {
        is NullCharSequence -> block()
        is NotNullOrEmptyCharSequence -> this.data
    }


fun CharSequence?.isValidatePassword(): Boolean {
    return this.notNullOrEmpty { Pattern.matches(REGEX_USERNAME, this) }.otherwise { false }
}


fun CharSequence?.isValidateUseName(): Boolean {
    return this.notNullOrEmpty { Pattern.matches(REGEX_USERNAME, this) }.otherwise { false }
}

fun <T> CharSequence?.setToTextView(textView: TextView){
    textView.text=this
}
