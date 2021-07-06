package io.sandbox.app.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterDataSource(private val client: RickAndMortyClient) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>) = DEFAULT_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: DEFAULT_PAGE
        return try {
            val charactersResponse = withContext(Dispatchers.IO) { client.characters(page) }
            val prevPage = if (page == DEFAULT_PAGE) null else page.dec()
            LoadResult.Page(charactersResponse.results, prevPage, page.inc())
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

    private companion object {
        const val DEFAULT_PAGE = 1
    }
}