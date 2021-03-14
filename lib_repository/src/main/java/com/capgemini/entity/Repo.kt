package com.capgemini.entity
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo @JvmOverloads constructor(
    val id: Int, // 344879635
    val node_id: String, // MDEwOlJlcG9zaXRvcnkzNDQ4Nzk2MzU=
    val name: String, // Hello-World
    val full_name: String, // shakeelkhangit/Hello-World
    val `private`: Boolean, // false
    val owner: Owner,
    val html_url: String, // https://github.com/shakeelkhangit/Hello-World
    val description: String, // My first repository on GitHub!
    val fork: Boolean, // true
    val url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World
    val forks_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/forks
    val keys_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/keys{/key_id}
    val collaborators_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/collaborators{/collaborator}
    val teams_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/teams
    val hooks_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/hooks
    val issue_events_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/issues/events{/number}
    val events_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/events
    val assignees_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/assignees{/user}
    val branches_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/branches{/branch}
    val tags_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/tags
    val blobs_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/git/blobs{/sha}
    val git_tags_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/git/tags{/sha}
    val git_refs_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/git/refs{/sha}
    val trees_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/git/trees{/sha}
    val statuses_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/statuses/{sha}
    val languages_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/languages
    val stargazers_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/stargazers
    val contributors_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/contributors
    val subscribers_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/subscribers
    val subscription_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/subscription
    val commits_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/commits{/sha}
    val git_commits_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/git/commits{/sha}
    val comments_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/comments{/number}
    val issue_comment_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/issues/comments{/number}
    val contents_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/contents/{+path}
    val compare_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/compare/{base}...{head}
    val merges_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/merges
    val archive_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/{archive_format}{/ref}
    val downloads_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/downloads
    val issues_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/issues{/number}
    val pulls_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/pulls{/number}
    val milestones_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/milestones{/number}
    val notifications_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/notifications{?since,all,participating}
    val labels_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/labels{/name}
    val releases_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/releases{/id}
    val deployments_url: String, // https://api.github.com/repos/shakeelkhangit/Hello-World/deployments
    val created_at: String, // 2021-03-05T17:08:43Z
    val updated_at: String, // 2021-03-05T17:24:15Z
    val pushed_at: String, // 2021-03-05T18:03:37Z
    val git_url: String, // git://github.com/shakeelkhangit/Hello-World.git
    val ssh_url: String, // git@github.com:shakeelkhangit/Hello-World.git
    val clone_url: String, // https://github.com/shakeelkhangit/Hello-World.git
    val svn_url: String, // https://github.com/shakeelkhangit/Hello-World
    val homepage: String,
    val size: Int, // 2
    val stargazers_count: Int, // 0
    val watchers_count: Int, // 0
    @SerializedName("language") val language: String? = null, // null
    val has_issues: Boolean, // false
    val has_projects: Boolean, // true
    val has_downloads: Boolean, // true
    val has_wiki: Boolean, // true
    val has_pages: Boolean, // false
    val forks_count: Int, // 0
    @SerializedName("mirror_url") val mirror_url: String? = null, // null
    val archived: Boolean, // false
    val disabled: Boolean, // false
    val open_issues_count: Int, // 0
    @SerializedName("license")val license: License? = null, // null
    val forks: Int, // 0
    val open_issues: Int, // 0
    val watchers: Int, // 0
    val default_branch: String // master
):Parcelable
