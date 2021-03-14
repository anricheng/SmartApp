package com.capgemini.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Head(
    val label: String, // shakeelkhangit:forkdev
    val ref: String, // forkdev
    val sha: String, // 20cb292856841647722e234f18c703b4b6ca1d71
    val user: PullRequestUser,
    val repo: Repo
):Parcelable