package io.sandbox.di.module

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.sandbox.app.App

@Module
class AppModule {

    @Provides
    fun resources(app: App): Resources = app.resources
}