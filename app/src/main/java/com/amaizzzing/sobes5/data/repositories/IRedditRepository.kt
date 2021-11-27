package com.amaizzzing.sobes5.data.repositories

import androidx.paging.PagingData
import com.amaizzzing.sobes5.ui.models.CommentModel
import kotlinx.coroutines.flow.Flow

interface IRedditRepository {
    suspend fun getTopComments(): Flow<PagingData<CommentModel>>
}