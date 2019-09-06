package io.sandbox.data.domain

import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import io.sandbox.data.model.CharactersResponse
import javax.inject.Inject

class CharacterUseCase @Inject constructor(client: RickAndMortyClient) : UseCase<String, Character>(client::character)

class CharactersUseCase(client: RickAndMortyClient) : UseCase<Int, CharactersResponse>(client::characters)