package com.capgemini.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.atomic.AtomicInteger

@Parcelize
data class ReposPullRequestItem(
    val url: String, // https://api.github.com/repos/octocat/Hello-World/pulls/881
    val id: Int, // 585786900
    val node_id: String, // MDExOlB1bGxSZXF1ZXN0NTg1Nzg2OTAw
    val html_url: String, // https://github.com/octocat/Hello-World/pull/881
    val diff_url: String, // https://github.com/octocat/Hello-World/pull/881.diff
    val patch_url: String, // https://github.com/octocat/Hello-World/pull/881.patch
    val issue_url: String, // https://api.github.com/repos/octocat/Hello-World/issues/881
    val number: Int, // 881
    val state: String, // open
    val locked: Boolean, // false
    val title: String, // Forkdev
    val user: PullRequestUser,
    val body: String, // need to fork
    val created_at: String, // 2021-03-05T18:06:10Z
    val updated_at: String, // 2021-03-05T18:06:10Z
    @SerializedName("closed_at")val closed_at: String? = null, // null
    @SerializedName("merged_at")val merged_at: String? = null, // null
    val merge_commit_sha: String, // c6ceac5f908296bc36d130920b0aee660dad70cd
    @SerializedName("assignee")val assignee: String? = null, // null
    val assignees: List<String>,
    @SerializedName("requested_reviewers") val requested_reviewers: List<String>? =null,
    @SerializedName("requested_teams")val requested_teams: List<String>? = null,
    @SerializedName("labels")val labels: List<String>? =null,
    @SerializedName("milestone")val milestone: String? = null, // null
    val draft: Boolean, // false
    val commits_url: String, // https://api.github.com/repos/octocat/Hello-World/pulls/881/commits
    val review_comments_url: String, // https://api.github.com/repos/octocat/Hello-World/pulls/881/comments
    val review_comment_url: String, // https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}
    val comments_url: String, // https://api.github.com/repos/octocat/Hello-World/issues/881/comments
    val statuses_url: String, // https://api.github.com/repos/octocat/Hello-World/statuses/20cb292856841647722e234f18c703b4b6ca1d71
    val head: Head,
    val base: Base,
    val author_association: String, // NONE
    @SerializedName("auto_merge")val auto_merge: String? = null, // null
    @SerializedName("ctive_lock_reason")val active_lock_reason: String? = null, // null
    var openTime: Long,
    var countNumber: Int

):Parcelable
