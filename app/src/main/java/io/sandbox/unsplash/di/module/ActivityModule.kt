package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.unsplash.app.main.MainActivity
import io.sandbox.unsplash.di.ActivityScoped

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}