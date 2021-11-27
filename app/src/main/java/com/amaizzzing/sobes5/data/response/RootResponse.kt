package com.amaizzzing.sobes5.data.response

import com.google.gson.annotations.Expose

data class RootResponse(
    @Expose val data: DataResponse? = null
)