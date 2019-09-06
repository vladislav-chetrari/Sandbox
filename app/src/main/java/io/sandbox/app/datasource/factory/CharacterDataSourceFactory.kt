package io.sandbox.app.datasource.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.DataSource
import io.sandbox.app.datasource.CharacterDataSource
import io.sandbox.data.client.RickAndMortyClient
import io.sandbox.data.domain.CharactersUseCase
import io.sandbox.data.model.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDataSourceFactory @Inject constructor(
        private val client: RickAndMortyClient
) : DataSource.Factory<Int, Character>() {

    private val source = MutableLiveData<CharacterDataSource>()
    val sourceProgress: LiveData<Boolean> = switchMap(source, CharacterDataSource::progress)
    val sourceError: LiveData<Throwable> = switchMap(source, CharacterDataSource::error)

    override fun create(): DataSource<Int, Character> = CharacterDataSource(CharactersUseCase(client)).also(source::postValue)
}