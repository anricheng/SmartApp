package com.capgemini.entity

import android.os.Parcelable
import com.capgemini.config.HttpConfig
import kotlinx.android.parcel.Parcelize

val HTTP_CODE_DEFAULT = "-1"
val HTTP_CODE_SUCCESS = "200"
val HTTP_CODE_UNAUTHRIZED = "401"

data class BaseResponse<T>(
    val code: String = HTTP_CODE_DEFAULT,
    val data: T?,
    var description: String = ""
)

data class Error(val code: String, val description: String)

/**
 * Http 请求结果类
 */
sealed class Result<out T>
class ResultError(val error: Error) : Result<Error>()
class ResultSuccess<T>(val data: T?) : Result<T>()

inline fun <T> Result<T>.isSuccess(block: (T?) -> Result<T>) = when (this) {
    is ResultSuccess -> ResultSuccess(block(this.data))
    is ResultError -> this

}

inline fun <T> Result<T>.otherwise(block: (Error) -> Error) = when (this) {
    is ResultError -> ResultError(block(this.error))
    is ResultSuccess -> this
}

/**
 * Http 请求转换类
 */
fun <T> BaseResponse<T>.convert(): Result<*> = when (code) {
    HTTP_CODE_SUCCESS -> ResultSuccess(data)
    HTTP_CODE_UNAUTHRIZED -> {
        HttpConfig.login?.invoke()
        ResultError(Error(code, description))
    }
    else -> ResultError(Error(code, description))
}

@Parcelize
data class LoginResponse(val token: String?) : Parcelable
