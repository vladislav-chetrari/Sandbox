package io.sandbox.unsplash.app.base

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(private val dispatcher: JobDispatcher = JobDispatcher()) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = dispatcher.main() + job

    @CallSuper
    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    @CallSuper
    protected open fun onError(error: Throwable) = Timber.e(error)

    protected fun <T> launchOnIO(
            onError: (Throwable) -> Unit = this@BaseViewModel::onError,
            onSuccess: (T) -> Unit = {},
            block: suspend () -> T
    ) {
        launch {
            try {
                val kek = withContext(dispatcher.io()) { block() }
                onSuccess(kek)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected fun <T> mutableLiveData(initialValue: T) = MutableLiveData<T>().apply { value = initialValue }
}