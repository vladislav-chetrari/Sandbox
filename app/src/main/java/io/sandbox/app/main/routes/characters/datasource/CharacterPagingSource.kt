package io.sandbox.app.main.routes.characters.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.sandbox.data.network.client.CharactersClient
import io.sandbox.data.network.model.response.CharacterResponse

class CharacterPagingSource(
    private val client: CharactersClient,
    private val requestParams: RequestParams
) : PagingSource<Int, CharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? = null

    override suspend fun load(params: LoadParams<Int>) = try {
        val page = params.key
        val prevPage = page?.dec()
        val charactersResponse = client.characters(page, requestParams.name, requestParams.characterStatus)
        val results = charactersResponse.results
        val nextPage = if (results.isEmpty()) null else page?.inc() ?: 2
        LoadResult.Page(results, prevPage, nextPage)
    } catch (t: Throwable) {
        LoadResult.Error(t)
    }

    data class RequestParams(
        val name: String? = null,
        val characterStatus: String? = null
    )
}