package io.sandbox.app.main.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import io.sandbox.app.base.BaseViewModel
import io.sandbox.app.base.mutable
import io.sandbox.app.base.mutableLiveData
import io.sandbox.app.datasource.CharacterDataSource
import io.sandbox.app.datasource.factory.CharacterDataSourceFactory
import io.sandbox.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory
) : BaseViewModel() {

    private val combinedLoadStatesContainer = MutableStateFlow(CombinedLoadStatesContainer())
    private val combinedLoadStates: Flow<CombinedLoadStates>
        get() = combinedLoadStatesContainer
            .map { it.combinedLoadStates }
            .filterNotNull()

    val characters: Flow<PagingData<Character>> = Pager(config, pagingSourceFactory = dataSourceFactory).flow
        .cachedIn(viewModelScope)

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

    val searchCriteria = mutableLiveData(CharacterSearchCriteria())

    fun onListLoadStateChanged(state: CombinedLoadStates) = launch {
        combinedLoadStatesContainer.emit(CombinedLoadStatesContainer(state))
    }

    fun onSearch(name: String, status: String) {
        searchCriteria.mutable.postValue(CharacterSearchCriteria(name.trim(), status.trim()))
        val treatedName = if (name.isBlank()) null else name
        val treatedStatus = if (status.isBlank()) null else status.toLowerCase(Locale.ROOT)
        dataSourceFactory.requestParams = CharacterDataSource.RequestParams(treatedName, treatedStatus)
    }

    fun onSearchReset() {
        searchCriteria.mutable.postValue(CharacterSearchCriteria())
        dataSourceFactory.requestParams = CharacterDataSource.RequestParams()
    }

    fun onSearchCancel() = searchCriteria.mutable.postValue(searchCriteria.value)

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