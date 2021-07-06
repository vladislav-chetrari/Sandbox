package io.sandbox.data.client

import io.sandbox.data.RickAndMortyApi

class DefaultRickAndMortyClient(private val api: RickAndMortyApi) : RickAndMortyClient {

    override suspend fun characters(page: Int) = api.characters(page)

    override suspend fun character(id: String) = api.character(id)
}