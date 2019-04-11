package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.Provides
import io.sandbox.unsplash.data.RickAndMortyApi
import io.sandbox.unsplash.data.client.DefaultRickAndMortyClient
import io.sandbox.unsplash.data.client.RickAndMortyClient
import javax.inject.Singleton

@Module
class ClientModule {

    @Provides
    @Singleton
    fun rickAndMortyClient(api: RickAndMortyApi): RickAndMortyClient = DefaultRickAndMortyClient(api)
}