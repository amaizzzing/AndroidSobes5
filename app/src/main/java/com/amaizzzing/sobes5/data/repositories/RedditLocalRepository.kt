package com.amaizzzing.sobes5.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amaizzzing.sobes5.data.api.MAX_ITEMS
import com.amaizzzing.sobes5.data.entities.CommentsEntity
import com.amaizzzing.sobes5.data.services.database.db.RedditLocalDB
import kotlinx.coroutines.flow.Flow

class RedditLocalRepository(
    private val redditLocalDb: RedditLocalDB
): IRedditLocalRepository {
    override suspend fun insertComment(comment: CommentsEntity) {
        redditLocalDb.commentsDao.insert(comment)
    }

    override suspend fun getAllComments(): Flow<PagingData<CommentsEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                enablePlaceholders = false
            )
        ) {
            redditLocalDb
                .commentsDao
                .getAllComments()
        }.flow
    }

}