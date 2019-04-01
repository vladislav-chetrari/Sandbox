package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.unsplash.app.main.MainActivity
import io.sandbox.unsplash.di.ActivityScope

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}