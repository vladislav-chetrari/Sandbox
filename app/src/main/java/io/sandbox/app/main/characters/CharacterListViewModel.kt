package io.sandbox.app.main.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import io.sandbox.app.base.view.list.ListLoadStatesContext
import io.sandbox.app.base.vm.BaseViewModel
import io.sandbox.app.main.characters.datasource.CharacterDataSource
import io.sandbox.app.main.characters.datasource.factory.CharacterDataSourceFactory
import io.sandbox.data.model.Character
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory,
    private val loadStatesContext: ListLoadStatesContext
) : BaseViewModel() {

    val searchCriteria = mutableLiveData(CharacterSearchCriteria())

    val characters: Flow<PagingData<Character>> = Pager(config, pagingSourceFactory = dataSourceFactory)
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
        val treatedStatus = if (status.isBlank()) null else status.toLowerCase(Locale.ROOT)
        dataSourceFactory.params = CharacterDataSource.RequestParams(treatedName, treatedStatus)
    }

    fun onSearchReset() {
        searchCriteria.mutable.postValue(CharacterSearchCriteria())
        dataSourceFactory.params = CharacterDataSource.RequestParams()
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