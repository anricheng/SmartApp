package com.capgemini.api

import com.capgemini.entity.BaseResponse
import com.capgemini.entity.Feed
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface CommunityApi {
    @GET("/feeds/queryHotFeedsList")
    fun getFeeds(
        @Query("feedType") feedType: String,
        @Query("userId") userId: String,
        @Query("feedId") feedId: String,
        @Query("pageCount") pageCount: String,
    ): BaseResponse<ArrayList<Feed>>
}