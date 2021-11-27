package com.amaizzzing.sobes5.data.services.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amaizzzing.sobes5.data.entities.CommentsEntity
import com.amaizzzing.sobes5.data.services.database.dao.CommentsDao

@Database(
    entities = [CommentsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RedditLocalDB: RoomDatabase() {
    abstract val commentsDao: CommentsDao

    companion object {
        const val DB_NAME = "reddit.db"

        fun newInstance(context: Context) = Room.databaseBuilder(
            context,
            RedditLocalDB::class.java,
            DB_NAME
        ).build()
    }
}