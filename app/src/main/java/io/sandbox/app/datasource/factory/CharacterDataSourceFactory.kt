package io.sandbox.app.datasource.factory

import androidx.paging.PagingSource
import io.sandbox.app.datasource.CharacterDataSource
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDataSourceFactory @Inject constructor(
    private val client: RickAndMortyClient
) : () -> PagingSource<Int, Character> {

    private var dataSource: CharacterDataSource? = null
    var requestParams: CharacterDataSource.RequestParams = CharacterDataSource.RequestParams()
        set(value) {
            field = value
            invalidate()
        }

    override operator fun invoke(): CharacterDataSource {
        val ds = CharacterDataSource(client, requestParams)
        dataSource = ds
        return ds
    }

    private fun invalidate() {
        dataSource?.invalidate()
    }
}