package io.sandbox.data.client

import io.sandbox.data.RickAndMortyApi
import io.sandbox.data.model.Character

class DefaultRickAndMortyClient(private val api: RickAndMortyApi) : RickAndMortyClient {

    override fun characters(page: Int): List<Character> = api.characters(page).body().results

    override fun character(id: String): Character = api.character(id).body()
}