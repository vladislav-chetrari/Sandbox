package io.sandbox.app.main.characters.datasource.factory

import io.sandbox.app.base.view.list.paging.BaseDataSourceFactory
import io.sandbox.app.main.characters.datasource.CharacterDataSource
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDataSourceFactory @Inject constructor(
    private val client: RickAndMortyClient
) : BaseDataSourceFactory<Int, Character, CharacterDataSource.RequestParams>(CharacterDataSource.RequestParams()) {

    override fun createDataSource(
        parameters: CharacterDataSource.RequestParams
    ) = CharacterDataSource(client, parameters)
}