package com.capgemini.api

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubToken {

    @POST("access_token/")
    suspend fun getToken(
            @Query("client_id")client_id: String,
            @Query("client_secret")client_secret: String,
            @Query("code")code: String
    ): Response<*>
}