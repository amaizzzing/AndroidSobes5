package com.amaizzzing.sobes5.data.repositories

import androidx.paging.PagingData
import com.amaizzzing.sobes5.data.entities.CommentsEntity
import kotlinx.coroutines.flow.Flow

interface IRedditLocalRepository {
    suspend fun insertComment(comment: CommentsEntity)

    suspend fun getAllComments(): Flow<PagingData<CommentsEntity>>
}