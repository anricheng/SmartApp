package com.capgemini.api

import com.capgemini.entity.ReposDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposDetailsApi {
    @GET("repos/{owner}/{repo}")
    fun getReposDetails(@Path("owner") owner: String, @Path("repo") repo: String): Call<ReposDetails>
}