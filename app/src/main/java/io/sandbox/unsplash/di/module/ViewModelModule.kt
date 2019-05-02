package io.sandbox.unsplash.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.sandbox.unsplash.app.base.ViewModelFactory
import io.sandbox.unsplash.app.main.home.HomeViewModel
import io.sandbox.unsplash.app.main.home.character.CharacterViewModel
import io.sandbox.unsplash.di.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    internal abstract fun characterViewModel(viewModel: CharacterViewModel): ViewModel
}