package io.sandbox.app.main.routes.characters.datasource.factory

import io.sandbox.app.base.view.list.paging.BasePagingSourceFactory
import io.sandbox.app.main.routes.characters.datasource.CharacterPagingSource
import io.sandbox.app.main.routes.characters.datasource.CharacterPagingSource.RequestParams
import io.sandbox.data.network.client.CharactersClient
import io.sandbox.data.network.model.response.CharacterResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterPagingSourceFactory @Inject constructor(
    private val client: CharactersClient
) : BasePagingSourceFactory<Int, CharacterResponse, RequestParams>(RequestParams()) {

    override fun createDataSource(parameters: RequestParams) = CharacterPagingSource(client, parameters)
}