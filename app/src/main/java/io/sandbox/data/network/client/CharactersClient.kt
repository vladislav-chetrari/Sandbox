package io.sandbox.data.network.client

import io.sandbox.data.network.model.response.CharacterResponse
import io.sandbox.data.network.model.response.CharactersResponse

interface CharactersClient {
    suspend fun characters(page: Int?, name: String?, status: String?): CharactersResponse
    suspend fun character(id: String): CharacterResponse
}