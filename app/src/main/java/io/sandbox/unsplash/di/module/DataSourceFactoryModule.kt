package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.Provides
import io.sandbox.unsplash.app.datasource.factory.CharacterDataSourceFactory
import io.sandbox.unsplash.data.client.RickAndMortyClient
import javax.inject.Singleton

@Module
class DataSourceFactoryModule {

    @Provides
    @Singleton
    fun characterDataSourceFactory(client: RickAndMortyClient) = CharacterDataSourceFactory(client)
}