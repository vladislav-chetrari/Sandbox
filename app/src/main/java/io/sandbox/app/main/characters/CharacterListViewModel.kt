package io.sandbox.app.main.characters

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.sandbox.app.base.BaseViewModel
import io.sandbox.app.base.JobDispatcher
import io.sandbox.app.datasource.factory.CharacterDataSourceFactory
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    dispatcher: JobDispatcher,
    private val factory: CharacterDataSourceFactory
) : BaseViewModel(dispatcher) {

    val characters = LivePagedListBuilder(factory, config).build()
    val progress: LiveData<Boolean> = factory.sourceProgress

    init {
        factory.sourceError.observeForever(::onError)
    }

    override fun onCleared() {
        factory.sourceError.removeObserver(::onError)
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