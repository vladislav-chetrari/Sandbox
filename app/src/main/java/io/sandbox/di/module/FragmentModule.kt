package io.sandbox.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sandbox.app.main.characters.CharacterListFragment
import io.sandbox.app.main.characters.character.CharacterDetailsFragment
import io.sandbox.app.main.graph.ArrayItemFragment
import io.sandbox.app.main.motion.MotionFragment
import io.sandbox.app.main.multitouch.MultitouchFragment
import io.sandbox.app.main.springs.SpringsFragment
import io.sandbox.di.FragmentScoped

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun homeFragment(): CharacterListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun titleFragment(): CharacterDetailsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun multitouchFragment(): MultitouchFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun arrayItemFragment(): ArrayItemFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun springsFragment(): SpringsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun motionFragment(): MotionFragment
}