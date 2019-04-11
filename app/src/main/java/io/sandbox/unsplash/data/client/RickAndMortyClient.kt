package io.sandbox.unsplash.data.client

import io.sandbox.unsplash.data.model.Character

interface RickAndMortyClient {
    fun characters(page: Int): List<Character>
}