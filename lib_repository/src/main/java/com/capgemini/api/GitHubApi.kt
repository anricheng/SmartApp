package com.capgemini.api

import com.capgemini.entity.GithubUser
import com.capgemini.entity.RecentEvent
import com.capgemini.entity.ReposDetails
import com.capgemini.entity.ReposPullRequestItem
import com.capgemini.entity.RepositoriesItem
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("/repos/{owner}/{repo}")
    suspend fun getReposDetails(@Path("owner") owner: String, @Path("repo") repo: String): ReposDetails


    @GET("/users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String):List<RepositoriesItem>

    @GET("/repos/{ownername}/{reponame}/pulls")
    suspend fun getReposPullRequest(@Path("ownername") ownername: String,@Path("reponame") reponame: String):List<ReposPullRequestItem>

    @GET("/users/{username}")
    suspend fun getGithubUser(@Path("username") username: String): GithubUser

    @GET("/users/{username}/received_events")
    suspend fun getRecentEvent(@Path("username") username: String): List<RecentEvent>
}