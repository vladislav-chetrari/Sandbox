package io.sandbox.app.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
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

    protected fun <T> liveData(initialValue: T? = null): LiveData<T> {
        val liveData = MutableLiveData<T>()
        initialValue?.let(liveData::setValue)
        return liveData
    }

    protected val <T> LiveData<T>.mutable: MutableLiveData<T>
        get() = this as MutableLiveData<T>

    protected fun <T> launchOnIO(
        onError: (Throwable) -> Unit = this@BaseViewModel::onError,
        onSuccess: (T) -> Unit = {},
        block: suspend () -> T
    ) {
        launch {
            try {
                val result = withContext(dispatcher.io()) { block() }
                onSuccess(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    @CallSuper
    protected open fun onError(error: Throwable) = Timber.e(error)

    @CallSuper
    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}