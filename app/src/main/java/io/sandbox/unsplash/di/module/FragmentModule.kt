package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.unsplash.app.main.home.HomeFragment
import io.sandbox.unsplash.app.main.home.title.TitleFragment
import io.sandbox.unsplash.di.FragmentScoped

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun titleFragment(): TitleFragment
}