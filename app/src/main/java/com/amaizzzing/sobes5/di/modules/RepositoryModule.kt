package com.amaizzzing.sobes5.di.modules

import com.amaizzzing.sobes5.data.api.IRedditDataSource
import com.amaizzzing.sobes5.data.repositories.IRedditLocalRepository
import com.amaizzzing.sobes5.data.repositories.IRedditRepository
import com.amaizzzing.sobes5.data.repositories.RedditLocalRepository
import com.amaizzzing.sobes5.data.repositories.RedditRepository
import com.amaizzzing.sobes5.data.services.database.db.RedditLocalDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun redditRepository(api: IRedditDataSource, redditDb: RedditLocalDB): IRedditRepository =
        RedditRepository(api, redditDb)

    @Singleton
    @Provides
    fun redditLocalRepository(redditLocalDB: RedditLocalDB): IRedditLocalRepository =
        RedditLocalRepository(redditLocalDB)
}