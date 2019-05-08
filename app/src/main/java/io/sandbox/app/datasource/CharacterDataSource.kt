package io.sandbox.app.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.sandbox.app.base.ActionLiveData
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.model.Character

class CharacterDataSource(private val client: RickAndMortyClient) : PageKeyedDataSource<Int, Character>() {

    val progress = MutableLiveData<Boolean>()
    val error = ActionLiveData<Throwable>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) {
        progress.postValue(true)
        try {
            val characters = client.characters(1)
            progress.postValue(false)
            callback.onResult(characters, null, 2)
        } catch (t: Throwable) {
            error.postValue(t)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        progress.postValue(true)
        try {
            val characters = client.characters(params.key)
            progress.postValue(false)
            callback.onResult(characters, params.key.inc())
        } catch (t: Throwable) {
            error.postValue(t)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) = Unit
}