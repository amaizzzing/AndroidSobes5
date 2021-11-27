package com.amaizzzing.sobes5.data.api

import com.amaizzzing.sobes5.data.entities.CommentsEntity
import com.amaizzzing.sobes5.data.services.database.db.RedditLocalDB
import com.amaizzzing.sobes5.ui.models.CommentModel

class RedditPagingSource(
    private val api: IRedditDataSource,
    private val redditDB: RedditLocalDB
): BasePagingSource<CommentModel>() {
    override suspend fun getData(
        nextPageId: String,
        params: LoadParams<String>
    ): LoadResult.Page<String, CommentModel> {
        val response = api.getTop(
            after = nextPageId,
            limit = MAX_ITEMS.toString()
        )
        val comments = response.data?.children ?: listOf()
        comments
            .map {
                CommentsEntity(
                    author = it.data.author,
                    title = it.data.title,
                    count_comments = it.data.num_comments.toString(),
                    crosspots = it.data.num_crossposts.toString(),
                    created = it.data.created.toString(),
                    thumbnail = it.data.thumbnail
                )
            }
            .forEach { redditDB.commentsDao.insert(it) }
        val nextKey = if (comments.isEmpty()) {
            ""
        } else {
            response.data?.after
        }

        return LoadResult.Page(
            data = comments.map { CommentModel(it.data) },
            prevKey = if (nextPageId == FIRST_INDEX_ITEM) null else nextPageId,
            nextKey = nextKey
        )
    }
}