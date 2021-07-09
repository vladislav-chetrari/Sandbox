package io.sandbox.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.sandbox.data.client.DefaultRickAndMortyClient
import io.sandbox.data.client.RickAndMortyClient

@Module
@InstallIn(SingletonComponent::class)
abstract class ClientModule {

    @Binds
    abstract fun rickAndMortyClient(client: DefaultRickAndMortyClient): RickAndMortyClient
}