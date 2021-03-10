package com.capgemini.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @SerializedName("login")
    val login: String, // anricheng
    @SerializedName("id")
    val id: Int, // 15911700
    @SerializedName("node_id")
    val nodeId: String, // MDQ6VXNlcjE1OTExNzAw
    @SerializedName("avatar_url")
    val avatarUrl: String? = null, // https://avatars.githubusercontent.com/u/15911700?v=4
    @SerializedName("gravatar_id")
    val gravatarId: String? = null,
    @SerializedName("url")
    val url: String, // https://api.github.com/users/anricheng
    @SerializedName("html_url")
    val htmlUrl: String, // https://github.com/anricheng
    @SerializedName("followers_url")
    val followersUrl: String, // https://api.github.com/users/anricheng/followers
    @SerializedName("following_url")
    val followingUrl: String, // https://api.github.com/users/anricheng/following{/other_user}
    @SerializedName("gists_url")
    val gistsUrl: String, // https://api.github.com/users/anricheng/gists{/gist_id}
    @SerializedName("starred_url")
    val starredUrl: String, // https://api.github.com/users/anricheng/starred{/owner}{/repo}
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String, // https://api.github.com/users/anricheng/subscriptions
    @SerializedName("organizations_url")
    val organizationsUrl: String, // https://api.github.com/users/anricheng/orgs
    @SerializedName("repos_url")
    val reposUrl: String, // https://api.github.com/users/anricheng/repos
    @SerializedName("events_url")
    val eventsUrl: String, // https://api.github.com/users/anricheng/events{/privacy}
    @SerializedName("received_events_url")
    val receivedEventsUrl: String, // https://api.github.com/users/anricheng/received_events
    @SerializedName("type")
    val type: String, // User
    @SerializedName("site_admin")
    val siteAdmin: Boolean, // false
    @SerializedName("name")
    val name: String, // Aric.chou
    @SerializedName("company")
    val company: String, // Capgemini
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String, // Shanghai,China
    @SerializedName("email")
    val email: String? = null, // null
    @SerializedName("bio")
    val bio: String, // I am a android developer and also have interest in AI and WEB.
    @SerializedName("public_repos")
    val publicRepos: Int, // 267
    @SerializedName("public_gists")
    val publicGists: Int, // 0
    @SerializedName("followers")
    val followers: Int, // 6
    @SerializedName("following")
    val following: Int, // 27
    @SerializedName("created_at")
    val createdAt: String, // 2015-11-18T16:56:18Z
    @SerializedName("updated_at")
    val updatedAt: String // 2021-03-03T07:47:04Z
) : Parcelable