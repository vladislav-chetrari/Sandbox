package io.sandbox.data.network.model.response

data class CharactersResponse(
    val info: InfoResponse = InfoResponse(),
    val results: List<CharacterResponse> = emptyList()
)