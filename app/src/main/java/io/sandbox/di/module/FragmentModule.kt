package io.sandbox.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.app.main.characters.CharacterListFragment
import io.sandbox.app.main.characters.character.CharacterFragment
import io.sandbox.app.main.graph.ArrayItemFragment
import io.sandbox.app.main.multitouch.MultitouchFragment
import io.sandbox.di.FragmentScoped

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun homeFragment(): CharacterListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun titleFragment(): CharacterFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun multitouchFragment(): MultitouchFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun arrayItemFragment(): ArrayItemFragment
}