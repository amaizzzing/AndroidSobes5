package com.amaizzzing.sobes5.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

const val MAX_ITEMS = 50
const val FIRST_INDEX_ITEM = ""

abstract class BasePagingSource<T: Any>: PagingSource<String, T>() {
    override fun getRefreshKey(state: PagingState<String, T>): String? {
        return state.lastItemOrNull().toString()
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, T> {
        val nextPageId = params.key ?: FIRST_INDEX_ITEM
        return try {
            getData(nextPageId, params)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    abstract suspend fun getData(nextPageId: String, params: LoadParams<String>): LoadResult.Page<String, T>
}