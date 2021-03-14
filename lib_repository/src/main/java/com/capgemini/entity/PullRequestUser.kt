package com.capgemini.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequestUser(
    val login: String, // shakeelkhangit
    val id: Int, // 66819864
    val node_id: String, // MDQ6VXNlcjY2ODE5ODY0
    val avatar_url: String, // https://avatars.githubusercontent.com/u/66819864?v=4
    val gravatar_id: String,
    val url: String, // https://api.github.com/users/shakeelkhangit
    val html_url: String, // https://github.com/shakeelkhangit
    val followers_url: String, // https://api.github.com/users/shakeelkhangit/followers
    val following_url: String, // https://api.github.com/users/shakeelkhangit/following{/other_user}
    val gists_url: String, // https://api.github.com/users/shakeelkhangit/gists{/gist_id}
    val starred_url: String, // https://api.github.com/users/shakeelkhangit/starred{/owner}{/repo}
    val subscriptions_url: String, // https://api.github.com/users/shakeelkhangit/subscriptions
    val organizations_url: String, // https://api.github.com/users/shakeelkhangit/orgs
    val repos_url: String, // https://api.github.com/users/shakeelkhangit/repos
    val events_url: String, // https://api.github.com/users/shakeelkhangit/events{/privacy}
    val received_events_url: String, // https://api.github.com/users/shakeelkhangit/received_events
    val type: String, // User
    val site_admin: Boolean // false
):Parcelable