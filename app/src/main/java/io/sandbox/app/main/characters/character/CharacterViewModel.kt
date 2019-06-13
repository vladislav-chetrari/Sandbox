package io.sandbox.app.main.characters.character

import io.sandbox.app.base.BaseViewModel
import io.sandbox.app.base.JobDispatcher
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    dispatcher: JobDispatcher,
    private val client: RickAndMortyClient
) : BaseViewModel(dispatcher) {

    val character = liveData<Character>()

    fun onCharacterIdReceived(characterId: String) = launchOnIO {
        val char = client.character(characterId)
        character.mutable.postValue(char)
    }
}