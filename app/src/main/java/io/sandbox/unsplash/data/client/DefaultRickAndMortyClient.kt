package io.sandbox.unsplash.data.client

import io.sandbox.unsplash.data.RickAndMortyApi
import io.sandbox.unsplash.data.model.Character

class DefaultRickAndMortyClient(private val api: RickAndMortyApi) : RickAndMortyClient {

    override fun characters(page: Int): List<Character> = api.characters(page).body().results

    override fun character(id: String): Character = api.character(id).body()
}