package io.sandbox.unsplash.app.datasource.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.DataSource
import io.sandbox.unsplash.app.datasource.CharacterDataSource
import io.sandbox.unsplash.data.client.RickAndMortyClient
import io.sandbox.unsplash.data.model.Character

class CharacterDataSourceFactory(private val client: RickAndMortyClient) : DataSource.Factory<Int, Character>() {

    private val source = MutableLiveData<CharacterDataSource>()
    val sourceProgress: LiveData<Boolean> = switchMap(source, CharacterDataSource::progress)
    val sourceError: LiveData<Throwable> = switchMap(source, CharacterDataSource::error)

    override fun create(): DataSource<Int, Character> = CharacterDataSource(client).also(source::postValue)
}