package io.sandbox.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.app.main.MainActivity
import io.sandbox.di.ActivityScoped

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}