package com.amaizzzing.sobes5.di.modules

import android.widget.ImageView
import androidx.room.Room
import com.amaizzzing.sobes5.RedditApp
import com.amaizzzing.sobes5.data.services.GlideImageViewLoader
import com.amaizzzing.sobes5.data.services.IImageLoader
import com.amaizzzing.sobes5.data.services.database.db.RedditLocalDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun imageLoader(): IImageLoader<ImageView> =
        GlideImageViewLoader()

    @Singleton
    @Provides
    fun database(app: RedditApp):RedditLocalDB =
        Room.databaseBuilder(
            app,
            RedditLocalDB::class.java,
            RedditLocalDB.DB_NAME
        ).build()
}