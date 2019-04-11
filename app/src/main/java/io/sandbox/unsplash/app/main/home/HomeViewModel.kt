package io.sandbox.unsplash.app.main.home

import androidx.lifecycle.MutableLiveData
import io.sandbox.unsplash.app.base.BaseViewModel
import io.sandbox.unsplash.app.base.JobDispatcher
import io.sandbox.unsplash.data.client.RickAndMortyClient
import io.sandbox.unsplash.data.model.Character
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dispatcher: JobDispatcher,
    private val client: RickAndMortyClient
) : BaseViewModel(dispatcher) {

    val characters = MutableLiveData<List<Character>>()

    init {
        launchOnIO(onSuccess = characters::setValue) { client.characters(1) }
    }
}