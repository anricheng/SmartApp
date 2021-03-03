package com.capgemini.api

import com.capgemini.entity.ReposDetails
import com.capgemini.entity.RepositoriesItem
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/repos/{owner}/{repo}")
    suspend fun getReposDetails(@Path("owner") owner: String, @Path("repo") repo: String): ReposDetails


    @GET("/users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String):List<RepositoriesItem>
}