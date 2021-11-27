package com.amaizzzing.sobes5.data.response

import com.google.gson.annotations.Expose

data class DataResponse(
    @Expose val children: List<RedditChildrenResponse>,
    @Expose val after: String?,
    @Expose val before: String?
)