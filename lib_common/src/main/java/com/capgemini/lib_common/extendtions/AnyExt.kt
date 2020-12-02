package com.capgemini.lib_common.extendtions

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

