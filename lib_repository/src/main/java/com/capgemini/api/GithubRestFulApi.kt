package com.capgemini.api

import com.capgemini.entity.BaseResponse
import com.capgemini.entity.Feed
import com.capgemini.entity.UserRespositoriesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface GithubRestFulApi {
    @GET("/users/{username}/repos")
   suspend fun getRepositories(@Path("username") username: String): Response<ArrayList<UserRespositoriesItem>>
}