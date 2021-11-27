package com.amaizzzing.sobes5.data.api

import com.amaizzzing.sobes5.data.response.RootResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IRedditDataSource {
    @GET("top.json?")
    suspend fun getTop(
        @Query("after") after: String?,
        @Query("limit") limit: String
    ): RootResponse
}