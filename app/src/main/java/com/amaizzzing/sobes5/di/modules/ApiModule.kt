package com.amaizzzing.sobes5.di.modules

import com.amaizzzing.sobes5.RedditApp
import com.amaizzzing.sobes5.data.api.IRedditDataSource
import com.amaizzzing.sobes5.data.services.network.AndroidNetworkStatus
import com.amaizzzing.sobes5.data.services.network.INetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun movieApi(gson: Gson) =
        Retrofit
            .Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient())
            .build()
            .create(IRedditDataSource::class.java)

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun httpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    @Provides
    @Singleton
    fun networkStatus(app: RedditApp): INetworkStatus = AndroidNetworkStatus(app)
}