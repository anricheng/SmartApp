package com.capgemini.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReposDetails(
    @SerializedName("id")
    val id: Int, // 76013351
    @SerializedName("node_id")
    val nodeId: String, // MDEwOlJlcG9zaXRvcnk3NjAxMzM1MQ==
    @SerializedName("name")
    val name: String, // MyHttp
    @SerializedName("full_name")
    val fullName: String, // anricheng/MyHttp
    @SerializedName("private")
    val isPrivate: Boolean, // false
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("html_url")
    val htmlUrl: String, // https://github.com/anricheng/MyHttp
    @SerializedName("description")
    val description: String? = null, // null
    @SerializedName("fork")
    val fork: Boolean, // false
    @SerializedName("url")
    val url: String, // https://api.github.com/repos/anricheng/MyHttp
    @SerializedName("forks_url")
    val forksUrl: String, // https://api.github.com/repos/anricheng/MyHttp/forks
    @SerializedName("keys_url")
    val keysUrl: String, // https://api.github.com/repos/anricheng/MyHttp/keys{/key_id}
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/collaborators{/collaborator}
    @SerializedName("teams_url")
    val teamsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/teams
    @SerializedName("hooks_url")
    val hooksUrl: String, // https://api.github.com/repos/anricheng/MyHttp/hooks
    @SerializedName("issue_events_url")
    val issueEventsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/issues/events{/number}
    @SerializedName("events_url")
    val eventsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/events
    @SerializedName("assignees_url")
    val assigneesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/assignees{/user}
    @SerializedName("branches_url")
    val branchesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/branches{/branch}
    @SerializedName("tags_url")
    val tagsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/tags
    @SerializedName("blobs_url")
    val blobsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/git/blobs{/sha}
    @SerializedName("git_tags_url")
    val gitTagsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/git/tags{/sha}
    @SerializedName("git_refs_url")
    val gitRefsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/git/refs{/sha}
    @SerializedName("trees_url")
    val treesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/git/trees{/sha}
    @SerializedName("statuses_url")
    val statusesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/statuses/{sha}
    @SerializedName("languages_url")
    val languagesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/languages
    @SerializedName("stargazers_url")
    val stargazersUrl: String, // https://api.github.com/repos/anricheng/MyHttp/stargazers
    @SerializedName("contributors_url")
    val contributorsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/contributors
    @SerializedName("subscribers_url")
    val subscribersUrl: String, // https://api.github.com/repos/anricheng/MyHttp/subscribers
    @SerializedName("subscription_url")
    val subscriptionUrl: String, // https://api.github.com/repos/anricheng/MyHttp/subscription
    @SerializedName("commits_url")
    val commitsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/commits{/sha}
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/git/commits{/sha}
    @SerializedName("comments_url")
    val commentsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/comments{/number}
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String, // https://api.github.com/repos/anricheng/MyHttp/issues/comments{/number}
    @SerializedName("contents_url")
    val contentsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/contents/{+path}
    @SerializedName("compare_url")
    val compareUrl: String, // https://api.github.com/repos/anricheng/MyHttp/compare/{base}...{head}
    @SerializedName("merges_url")
    val mergesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/merges
    @SerializedName("archive_url")
    val archiveUrl: String, // https://api.github.com/repos/anricheng/MyHttp/{archive_format}{/ref}
    @SerializedName("downloads_url")
    val downloadsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/downloads
    @SerializedName("issues_url")
    val issuesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/issues{/number}
    @SerializedName("pulls_url")
    val pullsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/pulls{/number}
    @SerializedName("milestones_url")
    val milestonesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/milestones{/number}
    @SerializedName("notifications_url")
    val notificationsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/notifications{?since,all,participating}
    @SerializedName("labels_url")
    val labelsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/labels{/name}
    @SerializedName("releases_url")
    val releasesUrl: String, // https://api.github.com/repos/anricheng/MyHttp/releases{/id}
    @SerializedName("deployments_url")
    val deploymentsUrl: String, // https://api.github.com/repos/anricheng/MyHttp/deployments
    @SerializedName("created_at")
    val createdAt: String, // 2016-12-09T07:42:02Z
    @SerializedName("updated_at")
    val updatedAt: String, // 2016-12-13T02:35:51Z
    @SerializedName("pushed_at")
    val pushedAt: String, // 2017-05-11T14:53:21Z
    @SerializedName("git_url")
    val gitUrl: String, // git://github.com/anricheng/MyHttp.git
    @SerializedName("ssh_url")
    val sshUrl: String, // git@github.com:anricheng/MyHttp.git
    @SerializedName("clone_url")
    val cloneUrl: String, // https://github.com/anricheng/MyHttp.git
    @SerializedName("svn_url")
    val svnUrl: String, // https://github.com/anricheng/MyHttp
    @SerializedName("homepage")
    val homepage: String? = null, // null
    @SerializedName("size")
    val size: Int, // 144
    @SerializedName("stargazers_count")
    val stargazersCount: Int, // 1
    @SerializedName("watchers_count")
    val watchersCount: Int, // 1
    @SerializedName("language")
    val language: String, // Java
    @SerializedName("has_issues")
    val hasIssues: Boolean, // true
    @SerializedName("has_projects")
    val hasProjects: Boolean, // true
    @SerializedName("has_downloads")
    val hasDownloads: Boolean, // true
    @SerializedName("has_wiki")
    val hasWiki: Boolean, // true
    @SerializedName("has_pages")
    val hasPages: Boolean, // false
    @SerializedName("forks_count")
    val forksCount: Int, // 0
    @SerializedName("mirror_url")
    val mirrorUrl: String? = null, // null
    @SerializedName("archived")
    val archived: Boolean, // false
    @SerializedName("disabled")
    val disabled: Boolean, // false
    @SerializedName("open_issues_count")
    val openIssuesCount: Int, // 0
    @SerializedName("license")
    val license: String? = null, // null
    @SerializedName("forks")
    val forks: Int, // 0
    @SerializedName("open_issues")
    val openIssues: Int, // 0
    @SerializedName("watchers")
    val watchers: Int, // 1
    @SerializedName("default_branch")
    val defaultBranch: String, // master
    @SerializedName("temp_clone_token")
    val tempCloneToken: String? = null, // null
    @SerializedName("network_count")
    val networkCount: Int, // 0
    @SerializedName("subscribers_count")
    val subscribersCount: Int // 1
) : Parcelable