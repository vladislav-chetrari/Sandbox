package io.sandbox.data.network.client

import io.sandbox.data.network.RickAndMortyApi
import io.sandbox.data.network.model.response.CharacterResponse
import io.sandbox.data.network.model.response.CharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCharactersClient @Inject constructor(
    private val api: RickAndMortyApi
) : CharactersClient {

    override suspend fun characters(
        page: Int?, name: String?, status: String?
    ): CharactersResponse = try {
        withContext(Dispatchers.IO) { api.characters(page, name, status) }
    } catch (e: HttpException) {
        if (e.code() == 404) {
            CharactersResponse()
        } else {
            throw e
        }
    }

    override suspend fun character(
        id: String
    ): CharacterResponse = withContext(Dispatchers.IO) { api.character(id) }
}