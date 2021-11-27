package com.amaizzzing.sobes5.data.services.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amaizzzing.sobes5.data.entities.CommentsEntity

@Dao
interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: CommentsEntity)

    @Query("select * from commentsentity order by created desc")
    fun getAllComments(): PagingSource<Int, CommentsEntity>
}