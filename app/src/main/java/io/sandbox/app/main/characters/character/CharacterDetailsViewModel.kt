package io.sandbox.app.main.characters.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import io.sandbox.app.base.vm.BaseViewModel
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val client: RickAndMortyClient
) : BaseViewModel() {

    private val characterId: MutableStateFlow<String> = MutableStateFlow("")
    val character: LiveData<Character> = characterId
        .filter { it.isNotEmpty() }
        .map(client::character)
        .asLiveData(Dispatchers.Default)

    fun onCharacterIdReceived(characterId: String) = launch { this.characterId.emit(characterId) }
}