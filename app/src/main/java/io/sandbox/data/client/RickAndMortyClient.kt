package io.sandbox.data.client

import io.sandbox.data.model.Character
import io.sandbox.data.model.CharactersResponse

interface RickAndMortyClient {
    suspend fun characters(page: Int): CharactersResponse
    suspend fun character(id: String): Character
}