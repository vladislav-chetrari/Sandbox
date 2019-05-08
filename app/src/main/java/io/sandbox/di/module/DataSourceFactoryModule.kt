package io.sandbox.di.module

import dagger.Module
import dagger.Provides
import io.sandbox.app.datasource.factory.CharacterDataSourceFactory
import io.sandbox.data.client.RickAndMortyClient
import javax.inject.Singleton

@Module
class DataSourceFactoryModule {

    @Provides
    @Singleton
    fun characterDataSourceFactory(client: RickAndMortyClient) = CharacterDataSourceFactory(client)
}