package io.sandbox.unsplash.data.model

data class CharactersResponse(
    val info: Info = Info(),
    val results: List<Character> = emptyList()
)