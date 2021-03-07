package com.capgemini.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubOAuth(
    @SerializedName("access_token")
    val accessToken: String, // f231537181ef896e0010b97c1741dae87e26505b
    @SerializedName("token_type")
    val tokenType: String? = null, // bearer
    @SerializedName("scope")
    val scope: String? = null
) : Parcelable