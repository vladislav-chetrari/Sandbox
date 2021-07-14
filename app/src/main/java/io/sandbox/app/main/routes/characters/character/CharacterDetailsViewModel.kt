package io.sandbox.app.main.routes.characters.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.sandbox.app.base.vm.BaseViewModel
import io.sandbox.data.db.dao.CharacterDao
import io.sandbox.data.db.entity.Character
import io.sandbox.data.network.client.CharactersClient
import io.sandbox.data.network.model.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val client: CharactersClient,
    private val dao: CharacterDao
) : BaseViewModel() {

    private val characterId: MutableStateFlow<String> = MutableStateFlow("")
    val character: LiveData<Character> = characterId
        .filter { it.isNotEmpty() }
        .map(::characterById)
        .asLiveData(Dispatchers.Default)

    fun onCharacterIdReceived(id: String) = launch { characterId.emit(id) }

    private suspend fun characterById(id: String): Character {
        val persisted = dao.findById(id)
        if (persisted == null) {
            val fetched = client.character(id)
            dao.save(mapCharacterResponse(fetched))
        }
        return dao.findById(id)!!
    }

    private fun mapCharacterResponse(response: CharacterResponse): Character = Character(
        id = response.id,
        name = response.name,
        status = response.status,
        species = response.species,
        gender = response.gender,
        image = response.image
    )
}