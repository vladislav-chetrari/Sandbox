package io.sandbox.unsplash.di.module

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.sandbox.unsplash.app.App
import io.sandbox.unsplash.app.base.JobDispatcher
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun resources(app: App): Resources = app.resources

    @Provides
    @Singleton
    fun jobDispatcher(): JobDispatcher = JobDispatcher()
}