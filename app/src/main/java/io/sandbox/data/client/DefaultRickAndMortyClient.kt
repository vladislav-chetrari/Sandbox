package io.sandbox.data.client

import io.sandbox.data.RickAndMortyApi
import io.sandbox.data.model.CharactersResponse
import retrofit2.HttpException

class DefaultRickAndMortyClient(private val api: RickAndMortyApi) : RickAndMortyClient {

    override suspend fun characters(page: Int?, name: String?, status: String?): CharactersResponse = try {
        api.characters(page, name, status)
    } catch (e: HttpException) {
        if (e.code() == 404) {
            CharactersResponse()
        } else {
            throw e
        }
    }

    override suspend fun character(id: String) = api.character(id)
}