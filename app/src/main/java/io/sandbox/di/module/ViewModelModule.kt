package io.sandbox.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.sandbox.app.base.ViewModelFactory
import io.sandbox.app.main.characters.CharacterListViewModel
import io.sandbox.app.main.characters.character.CharacterDetailsViewModel
import io.sandbox.di.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    internal abstract fun characterListViewModel(viewModel: CharacterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    internal abstract fun characterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel
}