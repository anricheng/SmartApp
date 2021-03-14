package com.capgemini.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Base(
    val label: String, // octocat:master
    val ref: String, // master
    val sha: String, // 7fd1a60b01f91b314f59955a4e4d4e80d8edf11d
    val user: PullRequestUser,
    val repo: Repo
):Parcelable