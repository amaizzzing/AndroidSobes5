package com.amaizzzing.sobes5.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amaizzzing.sobes5.data.api.IRedditDataSource
import com.amaizzzing.sobes5.data.api.MAX_ITEMS
import com.amaizzzing.sobes5.data.api.RedditPagingSource
import com.amaizzzing.sobes5.data.services.database.db.RedditLocalDB
import com.amaizzzing.sobes5.ui.models.CommentModel
import kotlinx.coroutines.flow.Flow

class RedditRepository(
    private val api: IRedditDataSource,
    private val redditDb: RedditLocalDB
): IRedditRepository {
    override suspend fun getTopComments(): Flow<PagingData<CommentModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RedditPagingSource(api, redditDb)
            }
        ).flow
    }
}