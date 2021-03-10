package com.capgemini.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    @SerializedName("id")
    val id: Int, // 293316071
    @SerializedName("name")
    val name: String, // bellard/quickjs
    @SerializedName("url")
    val url: String // https://api.github.com/repos/bellard/quickjs
) : Parcelable