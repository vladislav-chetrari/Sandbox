package io.sandbox.app.main.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import io.sandbox.app.base.BaseViewModel
import io.sandbox.app.datasource.CharacterDataSource
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val characterClient: RickAndMortyClient
) : BaseViewModel() {

    private val combinedLoadStatesContainer = MutableStateFlow(CombinedLoadStatesContainer())
    private val combinedLoadStates: Flow<CombinedLoadStates>
        get() = combinedLoadStatesContainer
            .map { it.combinedLoadStates }
            .filterNotNull()

    val characters: Flow<PagingData<Character>> = Pager(config) {
        CharacterDataSource(characterClient)
    }.flow.cachedIn(viewModelScope)

    val isRefreshing: LiveData<Boolean> = combinedLoadStates
        .map { it.refresh }
        .map { it is LoadState.Loading }
        .asLiveData(Dispatchers.Default)

    val isNextPageLoading: LiveData<Boolean> = combinedLoadStates
        .map { it.append }
        .map { it is LoadState.Loading }
        .asLiveData(Dispatchers.Default)

    val pageLoadError: LiveData<Throwable> = combinedLoadStates
        .map { it.errorOrNull }
        .filterNotNull()
        .onEach { onError(it) }
        .asLiveData(Dispatchers.Default)

    fun onListLoadStateChanged(state: CombinedLoadStates) = launch {
        combinedLoadStatesContainer.emit(CombinedLoadStatesContainer(state))
    }

    private val CombinedLoadStates.errorOrNull: Throwable?
        get() = listOf(append, prepend, refresh)
            .filterIsInstance<LoadState.Error>()
            .filter { it.endOfPaginationReached.not() }
            .map(LoadState.Error::error)
            .firstOrNull()

    private class CombinedLoadStatesContainer(
        val combinedLoadStates: CombinedLoadStates? = null
    )

    private companion object {
        const val PAGE_SIZE = 20
        val config = PagingConfig(
            enablePlaceholders = true,
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE * 2
        )
    }
}