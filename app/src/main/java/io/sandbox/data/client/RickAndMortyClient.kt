package io.sandbox.data.client

import io.sandbox.data.model.Character
import io.sandbox.data.model.CharactersResponse

interface RickAndMortyClient {
    fun characters(page: Int): CharactersResponse
    fun character(id: String): Character
}