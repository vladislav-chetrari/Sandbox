package io.sandbox.unsplash.di.module

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.sandbox.unsplash.app.App

@Module
class AppModule {

    @Provides
    fun resources(app: App): Resources = app.resources
}