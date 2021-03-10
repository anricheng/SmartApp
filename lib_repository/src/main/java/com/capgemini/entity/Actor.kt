package com.capgemini.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(
    @SerializedName("id")
    val id: Int, // 66577
    @SerializedName("login")
    val login: String, // JakeWharton
    @SerializedName("display_login")
    val displayLogin: String, // JakeWharton
    @SerializedName("gravatar_id")
    val gravatarId: String? = null,
    @SerializedName("url")
    val url: String, // https://api.github.com/users/JakeWharton
    @SerializedName("avatar_url")
    val avatarUrl: String // https://avatars.githubusercontent.com/u/66577?
) : Parcelable