package com.capgemini.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecentEvent(
    @SerializedName("id")
    val id: String, // 15429318476
    @SerializedName("type")
    val type: String, // WatchEvent
    @SerializedName("actor")
    val actor: Actor,
    @SerializedName("repo")
    val repo: Repo,
    @SerializedName("public")
    val isPublic: Boolean, // true
    @SerializedName("created_at")
    val createdAt: String // 2021-03-06T02:59:22Z
) : Parcelable