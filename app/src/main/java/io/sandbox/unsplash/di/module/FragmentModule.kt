package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.unsplash.app.main.characters.CharacterListFragment
import io.sandbox.unsplash.app.main.characters.character.CharacterFragment
import io.sandbox.unsplash.di.FragmentScoped

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun homeFragment(): CharacterListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun titleFragment(): CharacterFragment
}