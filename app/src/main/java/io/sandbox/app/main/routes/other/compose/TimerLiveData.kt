package io.sandbox.app.main.routes.other.compose

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal class TimerLiveData(
    private val parentContext: CoroutineContext
) : MutableLiveData<Int>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = parentContext + Dispatchers.Default

    override fun onActive() {
        super.onActive()
        launch {
            var value = value ?: 0
            while (true) {
                delay(1000L)
                value += 1
                postValue(value)
            }
        }
    }

    override fun onInactive() {
        coroutineContext.job.cancelChildren()
        super.onInactive()
    }
}