package io.sandbox.data.client

import io.sandbox.data.model.Character

interface RickAndMortyClient {
    fun characters(page: Int): List<Character>
    fun character(id: String): Character
}