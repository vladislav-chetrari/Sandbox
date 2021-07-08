package io.sandbox.app.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


abstract class BaseViewModel : ViewModel() {

    @CallSuper
    protected open fun onError(error: Throwable?) {
        error?.let { Timber.e(it) }
    }

    protected fun launch(context: CoroutineContext = EmptyCoroutineContext, work: suspend () -> Unit) {
        viewModelScope.launch(context) { work() }
    }
}