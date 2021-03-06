package com.capgemini.api

import com.capgemini.entity.GitHubOAuth
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubToken {

    @Headers("Accept: application/json")
    @POST("access_token/")
    suspend fun getToken(
            @Query("client_id")client_id: String,
            @Query("client_secret")client_secret: String,
            @Query("code")code: String
    ): GitHubOAuth
}