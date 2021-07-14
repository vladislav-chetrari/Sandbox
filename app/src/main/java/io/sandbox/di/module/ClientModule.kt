package io.sandbox.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.sandbox.data.network.client.CharactersClient
import io.sandbox.data.network.client.DefaultCharactersClient

@Module
@InstallIn(SingletonComponent::class)
abstract class ClientModule {

    @Binds
    abstract fun charactersClient(client: DefaultCharactersClient): CharactersClient
}