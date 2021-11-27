package com.amaizzzing.sobes5.data.response

import com.google.gson.annotations.Expose

data class RedditNewsDataResponse(
    @Expose val author: String,
    @Expose val title: String,
    @Expose val num_comments: Int,
    @Expose val created: Long,
    @Expose val thumbnail: String,
    @Expose val url: String,
    @Expose val num_crossposts: Int
)