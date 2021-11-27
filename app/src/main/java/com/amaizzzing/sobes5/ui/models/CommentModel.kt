package com.amaizzzing.sobes5.ui.models

import com.amaizzzing.sobes5.data.entities.CommentsEntity
import com.amaizzzing.sobes5.data.response.RedditNewsDataResponse

data class CommentModel(
    val author: String,
    val title: String,
    val count_comments: String,
    val crosspots: String,
    val created: String,
    val thumbnail: String,
) {
    constructor(response: RedditNewsDataResponse): this(
        author = response.author,
        title = response.title,
        count_comments = response.num_comments.toString(),
        crosspots = response.num_crossposts.toString(),
        created = response.created.toString(),
        thumbnail = response.thumbnail
    )

    constructor(commentEntity: CommentsEntity): this(
        author = commentEntity.author,
        title = commentEntity.title,
        count_comments = commentEntity.count_comments,
        crosspots = commentEntity.crosspots,
        created = commentEntity.created,
        thumbnail = commentEntity.thumbnail
    )
}