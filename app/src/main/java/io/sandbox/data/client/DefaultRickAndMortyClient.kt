package io.sandbox.data.client

import io.sandbox.data.RickAndMortyApi

class DefaultRickAndMortyClient(private val api: RickAndMortyApi) : RickAndMortyClient {

    override fun characters(page: Int) = api.characters(page).body()

    override fun character(id: String) = api.character(id).body()
}