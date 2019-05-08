package io.sandbox.di.module

import dagger.Module
import dagger.Provides
import io.sandbox.data.RickAndMortyApi
import io.sandbox.data.client.DefaultRickAndMortyClient
import io.sandbox.data.client.RickAndMortyClient
import javax.inject.Singleton

@Module
class ClientModule {

    @Provides
    @Singleton
    fun rickAndMortyClient(api: RickAndMortyApi): RickAndMortyClient = DefaultRickAndMortyClient(api)
}