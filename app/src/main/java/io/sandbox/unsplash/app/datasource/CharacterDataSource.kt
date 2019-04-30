package io.sandbox.unsplash.app.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.sandbox.unsplash.data.client.RickAndMortyClient
import io.sandbox.unsplash.data.model.Character

class CharacterDataSource(private val client: RickAndMortyClient) : PageKeyedDataSource<Int, Character>() {

    private val _state = MutableLiveData<DataSourceState>()
    //TODO find a way to give this to view model
    val state: LiveData<DataSourceState> = _state

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) {
        _state.postValue(DataSourceState.Progress)
        try {
            val characters = client.characters(1)
            _state.postValue(DataSourceState.Success)
            callback.onResult(characters, null, 2)
        } catch (t: Throwable) {
            _state.postValue(DataSourceState.Fail(t))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        _state.postValue(DataSourceState.Progress)
        try {
            val characters = client.characters(params.key)
            _state.postValue(DataSourceState.Success)
            callback.onResult(characters, params.key.inc())
        } catch (t: Throwable) {
            _state.postValue(DataSourceState.Fail(t))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) = Unit
}