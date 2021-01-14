package com.capgemini.repository

import com.capgemini.api.CommunityApi
import com.capgemini.entity.BaseResponse
import com.capgemini.entity.Feed
import kotlinx.coroutines.Deferred
import java.util.*

class SampleRepository2(private val communityApi: CommunityApi) {

    suspend fun getFeeds(feedType: String, userId: String, feedId: String, pageCount: String): BaseResponse<ArrayList<Feed>> {
        return communityApi.getFeeds(feedType, userId, feedId, pageCount)
    }

}