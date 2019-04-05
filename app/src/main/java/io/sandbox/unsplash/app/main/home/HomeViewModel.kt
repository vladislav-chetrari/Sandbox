package io.sandbox.unsplash.app.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import io.sandbox.unsplash.app.base.BaseViewModel
import io.sandbox.unsplash.app.base.JobDispatcher
import javax.inject.Inject

class HomeViewModel @Inject constructor(dispatcher: JobDispatcher) : BaseViewModel(dispatcher) {

    private val liveRawList = mutableLiveData((0..100).toList())
    val liveList: LiveData<List<String>> = map(liveRawList) { it.map(Int::toString) }
}