package io.sandbox.app.main.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.sandbox.app.base.BaseViewModel
import io.sandbox.app.base.actionLiveData
import io.sandbox.app.datasource.factory.CharacterDataSourceFactory
import io.sandbox.data.domain.CharacterUseCase
import io.sandbox.data.domain.ResultLiveData
import io.sandbox.data.model.Character
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
        private val factory: CharacterDataSourceFactory,
        private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    private val resultCharacter = ResultLiveData<Character>()
    val character = actionLiveData(resultCharacter.result)
    val characters = LivePagedListBuilder(factory, config).build()
    val progress: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(factory.sourceProgress, ::postValue)
        addSource(resultCharacter.progress, ::postValue)
    }

    init {
        factory.sourceError.observeForever(::onError)
        resultCharacter.error.observeForever(::onError)
    }

    fun onCharacterSelected(character: Character) =
            characterUseCase(inputParam = character.id, consumer = this.resultCharacter::postValue)

    override fun onCleared() {
        factory.sourceError.removeObserver(::onError)
        resultCharacter.error.removeObserver(::onError)
        super.onCleared()
    }

    private companion object {
        const val PAGE_SIZE = 20
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .build()
    }
}