package com.amaizzzing.sobes5

import android.app.Application
import com.amaizzzing.sobes5.di.DaggerRedditComponent
import com.amaizzzing.sobes5.di.RedditComponent

class RedditApp: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        redditComponent = DaggerRedditComponent
            .builder()
            .application(this)
            .build()
    }

    companion object {
        lateinit var instance: RedditApp
        lateinit var redditComponent: RedditComponent
    }
}