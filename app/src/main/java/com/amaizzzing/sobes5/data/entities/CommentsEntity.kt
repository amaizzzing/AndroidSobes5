package com.amaizzzing.sobes5.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["author","title"])
data class CommentsEntity(
    val author: String,
    val title: String,
    val count_comments: String,
    val crosspots: String,
    val created: String,
    val thumbnail: String
)