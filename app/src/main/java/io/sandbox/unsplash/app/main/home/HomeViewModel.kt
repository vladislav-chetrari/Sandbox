package io.sandbox.unsplash.app.main.home

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.sandbox.unsplash.app.base.BaseViewModel
import io.sandbox.unsplash.app.base.JobDispatcher
import io.sandbox.unsplash.app.datasource.factory.CharacterDataSourceFactory
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dispatcher: JobDispatcher,
    factory: CharacterDataSourceFactory
) : BaseViewModel(dispatcher) {

    val characters = LivePagedListBuilder(factory, config).build()

    private companion object {
        const val PAGE_SIZE = 20
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()
    }
}