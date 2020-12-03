package com.capgemini.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class BaseResponse<T>(val code: String?, val data: T?, var description: String?)

@Parcelize
data class LoginResponse(val token: String?):Parcelable
