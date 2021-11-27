package com.amaizzzing.sobes5.di

import com.amaizzzing.sobes5.MainActivity
import com.amaizzzing.sobes5.RedditApp
import com.amaizzzing.sobes5.di.modules.ApiModule
import com.amaizzzing.sobes5.di.modules.RepositoryModule
import com.amaizzzing.sobes5.di.modules.ServiceModule
import com.amaizzzing.sobes5.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        ServiceModule::class
    ]
)
interface RedditComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RedditApp): Builder

        fun build(): RedditComponent
    }

    fun inject(mainActivity: MainActivity)
}