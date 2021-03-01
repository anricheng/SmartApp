package com.capgemini.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class UserInformation @JvmOverloads constructor(
    val id: Int, // 182908361
    val node_id: String, // MDEwOlJlcG9zaXRvcnkxODI5MDgzNjE=
    @SerializedName("name") val name: String? = null, // -
    val full_name: String, // anricheng/-
    val `private`: Boolean, // false
    val owner: Owners,
    val html_url: String, // https://github.com/anricheng/-
    @SerializedName("description") val description: String? = null, // null
    val fork: Boolean, // false
    val url: String, // https://api.github.com/repos/anricheng/-
    val forks_url: String, // https://api.github.com/repos/anricheng/-/forks
    val keys_url: String, // https://api.github.com/repos/anricheng/-/keys{/key_id}
    val collaborators_url: String, // https://api.github.com/repos/anricheng/-/collaborators{/collaborator}
    val teams_url: String, // https://api.github.com/repos/anricheng/-/teams
    val hooks_url: String, // https://api.github.com/repos/anricheng/-/hooks
    val issue_events_url: String, // https://api.github.com/repos/anricheng/-/issues/events{/number}
    val events_url: String, // https://api.github.com/repos/anricheng/-/events
    val assignees_url: String, // https://api.github.com/repos/anricheng/-/assignees{/user}
    val branches_url: String, // https://api.github.com/repos/anricheng/-/branches{/branch}
    val tags_url: String, // https://api.github.com/repos/anricheng/-/tags
    val blobs_url: String, // https://api.github.com/repos/anricheng/-/git/blobs{/sha}
    val git_tags_url: String, // https://api.github.com/repos/anricheng/-/git/tags{/sha}
    val git_refs_url: String, // https://api.github.com/repos/anricheng/-/git/refs{/sha}
    val trees_url: String, // https://api.github.com/repos/anricheng/-/git/trees{/sha}
    val statuses_url: String, // https://api.github.com/repos/anricheng/-/statuses/{sha}
    val languages_url: String, // https://api.github.com/repos/anricheng/-/languages
    val stargazers_url: String, // https://api.github.com/repos/anricheng/-/stargazers
    val contributors_url: String, // https://api.github.com/repos/anricheng/-/contributors
    val subscribers_url: String, // https://api.github.com/repos/anricheng/-/subscribers
    val subscription_url: String, // https://api.github.com/repos/anricheng/-/subscription
    val commits_url: String, // https://api.github.com/repos/anricheng/-/commits{/sha}
    val git_commits_url: String, // https://api.github.com/repos/anricheng/-/git/commits{/sha}
    val comments_url: String, // https://api.github.com/repos/anricheng/-/comments{/number}
    val issue_comment_url: String, // https://api.github.com/repos/anricheng/-/issues/comments{/number}
    val contents_url: String, // https://api.github.com/repos/anricheng/-/contents/{+path}
    val compare_url: String, // https://api.github.com/repos/anricheng/-/compare/{base}...{head}
    val merges_url: String, // https://api.github.com/repos/anricheng/-/merges
    val archive_url: String, // https://api.github.com/repos/anricheng/-/{archive_format}{/ref}
    val downloads_url: String, // https://api.github.com/repos/anricheng/-/downloads
    val issues_url: String, // https://api.github.com/repos/anricheng/-/issues{/number}
    val pulls_url: String, // https://api.github.com/repos/anricheng/-/pulls{/number}
    val milestones_url: String, // https://api.github.com/repos/anricheng/-/milestones{/number}
    val notifications_url: String, // https://api.github.com/repos/anricheng/-/notifications{?since,all,participating}
    val labels_url: String, // https://api.github.com/repos/anricheng/-/labels{/name}
    val releases_url: String, // https://api.github.com/repos/anricheng/-/releases{/id}
    val deployments_url: String, // https://api.github.com/repos/anricheng/-/deployments
    val created_at: String, // 2019-04-23T02:25:19Z
    val updated_at: String, // 2019-04-23T02:49:38Z
    val pushed_at: String, // 2019-04-23T02:49:36Z
    val git_url: String, // git://github.com/anricheng/-.git
    val ssh_url: String, // git@github.com:anricheng/-.git
    val clone_url: String, // https://github.com/anricheng/-.git
    val svn_url: String, // https://github.com/anricheng/-
    @SerializedName("homepage") val homepage: Int? = null, // null
    val size: Int, // 2
    val stargazers_count: Int, // 0
    val watchers_count: Int, // 0
    val language: String, // Python
    val has_issues: Boolean, // true
    val has_projects: Boolean, // true
    val has_downloads: Boolean, // true
    val has_wiki: Boolean, // true
    val has_pages: Boolean, // false
    val forks_count: Int, // 0
    @SerializedName("mirror_url") val mirror_url: String? = null, // null
    val archived: Boolean, // false
    val disabled: Boolean, // false
    val open_issues_count: Int, // 0
    @SerializedName("license") val license: String? = null, // null
    val forks: Int, // 0
    val open_issues: Int, // 0
    val watchers: Int, // 0
    val default_branch: String // master
) : Parcelable

@Parcelize
data class Owners(
    val login: String, // anricheng
    val id: Int, // 15911700
    val node_id: String, // MDQ6VXNlcjE1OTExNzAw
    val avatar_url: String, // https://avatars.githubusercontent.com/u/15911700?v=4
    @SerializedName("gravatar_id") val gravatar_id: String? = null,
    val url: String, // https://api.github.com/users/anricheng
    val html_url: String, // https://github.com/anricheng
    val followers_url: String, // https://api.github.com/users/anricheng/followers
    val following_url: String, // https://api.github.com/users/anricheng/following{/other_user}
    val gists_url: String, // https://api.github.com/users/anricheng/gists{/gist_id}
    val starred_url: String, // https://api.github.com/users/anricheng/starred{/owner}{/repo}
    val subscriptions_url: String, // https://api.github.com/users/anricheng/subscriptions
    val organizations_url: String, // https://api.github.com/users/anricheng/orgs
    val repos_url: String, // https://api.github.com/users/anricheng/repos
    val events_url: String, // https://api.github.com/users/anricheng/events{/privacy}
    val received_events_url: String, // https://api.github.com/users/anricheng/received_events
    val type: String, // User
    val site_admin: Boolean // false
):Parcelable