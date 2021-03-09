package com.capgemini.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    val self: Self,
    val html: Html,
    val issue: Issue,
    val comments: Comments,
    val review_comments: ReviewComments,
    val review_comment: ReviewComment,
    val commits: Commits,
    val statuses: Statuses
):Parcelable