package io.sandbox.app.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class BaseViewModel : ViewModel() {

    @CallSuper
    protected open fun onError(error: Throwable?) {
        error?.let { Timber.e(it) }
    }

    protected fun launch(work: suspend () -> Unit) {
        viewModelScope.launch { work() }
    }
}