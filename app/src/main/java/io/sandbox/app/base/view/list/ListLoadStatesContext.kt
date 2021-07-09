package io.sandbox.app.base.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListLoadStatesContext @Inject constructor() {

    private val combinedLoadStatesContainer = MutableStateFlow(CombinedLoadStatesContainer())

    private val combinedLoadStates: Flow<CombinedLoadStates>
        get() = combinedLoadStatesContainer
            .map { it.combinedLoadStates }
            .filterNotNull()

    val isRefreshing: LiveData<Boolean>
        get() = combinedLoadStates
            .map { it.refresh }
            .map { it is LoadState.Loading }
            .asLiveData(Dispatchers.Default)

    val isNextPageLoading: LiveData<Boolean>
        get() = combinedLoadStates
            .map { it.append }
            .map { it is LoadState.Loading }
            .asLiveData(Dispatchers.Default)

    suspend fun emit(combinedLoadStates: CombinedLoadStates) {
        combinedLoadStatesContainer.emit(CombinedLoadStatesContainer(combinedLoadStates))
    }

    fun pageLoadError(onEach: (Throwable) -> Unit): LiveData<Throwable> = combinedLoadStates
        .map { it.errorOrNull }
        .filterNotNull()
        .onEach { onEach(it) }
        .asLiveData(Dispatchers.Default)

    private val CombinedLoadStates.errorOrNull: Throwable?
        get() = listOf(append, prepend, refresh)
            .filterIsInstance<LoadState.Error>()
            .filter { it.endOfPaginationReached.not() }
            .map(LoadState.Error::error)
            .firstOrNull()

    private class CombinedLoadStatesContainer(
        val combinedLoadStates: CombinedLoadStates? = null
    )
}