package io.sandbox.unsplash.data.client

import io.sandbox.unsplash.data.model.Cat

interface CatSearchClient {
    fun all(page: Int): List<Cat>
}