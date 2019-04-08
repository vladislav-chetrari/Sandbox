package io.sandbox.unsplash.di.module

import dagger.Module
import dagger.Provides
import io.sandbox.unsplash.data.CatApi
import io.sandbox.unsplash.data.client.CatSearchClient
import io.sandbox.unsplash.data.client.DefaultCatSearchClient
import javax.inject.Singleton

@Module
class ClientModule {

    @Provides
    @Singleton
    fun catClient(api: CatApi): CatSearchClient = DefaultCatSearchClient(api)
}