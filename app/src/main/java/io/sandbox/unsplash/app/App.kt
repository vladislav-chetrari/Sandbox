package io.sandbox.unsplash.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.sandbox.unsplash.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.*

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<App> = DaggerAppComponent.builder().create(this)
}