package io.sandbox.unsplash.data.client

import io.sandbox.unsplash.data.CatApi

class DefaultCatSearchClient(private val api: CatApi) : CatSearchClient {

    override fun all(page: Int) = api.all(PAGE_SIZE, page, ORDER).body()

    private companion object {
        const val PAGE_SIZE = 20
        const val ORDER = "asc"
    }
}