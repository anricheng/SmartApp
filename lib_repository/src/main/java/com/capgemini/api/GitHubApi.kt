package com.capgemini.api

import com.capgemini.entity.ReposDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/repos/{owner}/{repo}")
    suspend fun getReposDetails(@Path("owner") owner: String, @Path("repo") repo: String): ReposDetails
}