package io.sandbox.unsplash.app.datasource.factory

import androidx.paging.DataSource
import io.sandbox.unsplash.app.datasource.CharacterDataSource
import io.sandbox.unsplash.data.client.RickAndMortyClient
import io.sandbox.unsplash.data.model.Character

class CharacterDataSourceFactory(private val client: RickAndMortyClient) : DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> = CharacterDataSource(client)
}