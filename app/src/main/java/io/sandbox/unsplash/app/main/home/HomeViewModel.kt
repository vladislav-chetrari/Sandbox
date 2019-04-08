package io.sandbox.unsplash.app.main.home

import androidx.lifecycle.MutableLiveData
import io.sandbox.unsplash.app.base.BaseViewModel
import io.sandbox.unsplash.app.base.JobDispatcher
import io.sandbox.unsplash.data.client.CatSearchClient
import io.sandbox.unsplash.data.model.Cat
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dispatcher: JobDispatcher,
    private val client: CatSearchClient
) : BaseViewModel(dispatcher) {

    val cats = MutableLiveData<List<Cat>>()

    init {
        launchOnIO(onSuccess = cats::setValue) { client.all(1) }
    }
}