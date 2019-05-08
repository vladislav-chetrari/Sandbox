package io.sandbox.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.sandbox.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<App> = DaggerAppComponent.builder().create(this)
}