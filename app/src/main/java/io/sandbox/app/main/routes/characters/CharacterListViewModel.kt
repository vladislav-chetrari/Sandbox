package io.sandbox.app.main.routes.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.sandbox.app.base.view.list.ListLoadStatesContext
import io.sandbox.app.base.vm.BaseViewModel
import io.sandbox.app.main.routes.characters.datasource.CharacterPagingSource
import io.sandbox.app.main.routes.characters.datasource.factory.CharacterPagingSourceFactory
import io.sandbox.data.network.model.response.CharacterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val dataSourceFactory: CharacterPagingSourceFactory,
    private val loadStatesContext: ListLoadStatesContext
) : BaseViewModel() {

    val searchCriteria = mutableLiveData(CharacterSearchCriteria())

    val characters: Flow<PagingData<CharacterResponse>> = Pager(config, pagingSourceFactory = dataSourceFactory)
        .flow.cachedIn(viewModelScope)

    val isRefreshing: LiveData<Boolean> = loadStatesContext.isRefreshing

    val isNextPageLoading: LiveData<Boolean> = loadStatesContext.isNextPageLoading

    val pageLoadError: LiveData<Throwable> = loadStatesContext.pageLoadError(::onError)

    fun onListLoadStateChanged(state: CombinedLoadStates) = launch {
        loadStatesContext.emit(state)
    }

    fun onSearch(name: String, status: String) {
        searchCriteria.mutable.postValue(CharacterSearchCriteria(name.trim(), status.trim()))
        val treatedName = if (name.isBlank()) null else name
        val treatedStatus = if (status.isBlank()) null else status.lowercase()
        dataSourceFactory.params = CharacterPagingSource.RequestParams(treatedName, treatedStatus)
    }

    fun onSearchReset() {
        searchCriteria.mutable.postValue(CharacterSearchCriteria())
        dataSourceFactory.params = CharacterPagingSource.RequestParams()
    }

    fun onSearchCancel() = searchCriteria.mutable.postValue(searchCriteria.value)

    private companion object {
        const val PAGE_SIZE = 20
        val config = PagingConfig(
            enablePlaceholders = true,
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE * 2
        )
    }
}